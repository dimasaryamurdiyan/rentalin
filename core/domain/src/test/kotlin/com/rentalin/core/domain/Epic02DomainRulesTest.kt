package com.rentalin.core.domain

import com.rentalin.core.common.repository.DashboardData
import com.rentalin.core.domain.availability.CheckQuantityAvailabilityUseCase
import com.rentalin.core.domain.availability.CheckSerializedAvailabilityUseCase
import com.rentalin.core.domain.dashboard.ObserveDashboardSummaryUseCase
import com.rentalin.core.domain.payment.CalculatePaymentSummaryUseCase
import com.rentalin.core.domain.rental.RentalLifecycleUseCase
import com.rentalin.core.domain.rental.RentalOperationalStateUseCase
import com.rentalin.core.domain.time.CurrentDateProvider
import com.rentalin.core.domain.validation.ValidateDateRangeUseCase
import com.rentalin.core.model.AvailabilityBlockReason
import com.rentalin.core.model.DateRange
import com.rentalin.core.model.MoneyAmount
import com.rentalin.core.model.PaymentStatus
import com.rentalin.core.model.RentalStatus
import com.rentalin.core.model.RentalValidationError
import com.rentalin.core.model.UnitStatus
import com.rentalin.core.testing.FakeDashboardRepository
import com.rentalin.core.testing.FakeInventoryRepository
import com.rentalin.core.testing.FakeRentalRepository
import com.rentalin.core.testing.RentalInFixtures
import java.time.LocalDate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Epic02DomainRulesTest {
    private val currentDate = LocalDate.of(2026, 5, 22)
    private val currentDateProvider = CurrentDateProvider { currentDate }

    @Test
    fun invalidDateRange_returnsValidationError() {
        val result = ValidateDateRangeUseCase()(
            startDate = LocalDate.of(2026, 5, 22),
            expectedReturnDate = LocalDate.of(2026, 5, 20),
        )

        assertEquals(RentalValidationError.InvalidDateRange, result)
    }

    @Test
    fun serializedOverlap_blocksReservedOrActiveRental() = runTest {
        val existingRental = RentalInFixtures.rental(status = RentalStatus.Reserved)
        val inventoryRepository = FakeInventoryRepository(
            items = listOf(RentalInFixtures.tentItem),
            units = listOf(RentalInFixtures.tentUnit),
        )
        val rentalRepository = FakeRentalRepository(listOf(existingRental))

        val result = CheckSerializedAvailabilityUseCase(
            inventoryRepository = inventoryRepository,
            rentalRepository = rentalRepository,
        )(
            unitId = RentalInFixtures.tentUnit.id,
            dateRange = DateRange(LocalDate.of(2026, 5, 21), LocalDate.of(2026, 5, 23)),
        )

        assertFalse(result.isAvailable)
        assertEquals(AvailabilityBlockReason.OverlappingRental, result.reason)
        assertEquals(listOf(existingRental.id), result.blockingRentalIds)
    }

    @Test
    fun returnedRental_doesNotBlockSerializedAvailability() = runTest {
        val existingRental = RentalInFixtures.rental(status = RentalStatus.Returned)
        val inventoryRepository = FakeInventoryRepository(
            items = listOf(RentalInFixtures.tentItem),
            units = listOf(RentalInFixtures.tentUnit),
        )
        val rentalRepository = FakeRentalRepository(listOf(existingRental))

        val result = CheckSerializedAvailabilityUseCase(
            inventoryRepository = inventoryRepository,
            rentalRepository = rentalRepository,
        )(
            unitId = RentalInFixtures.tentUnit.id,
            dateRange = DateRange(LocalDate.of(2026, 5, 21), LocalDate.of(2026, 5, 23)),
        )

        assertTrue(result.isAvailable)
    }

    @Test
    fun maintenanceUnit_isUnavailableRegardlessOfDate() = runTest {
        val maintenanceUnit = RentalInFixtures.tentUnit.copy(status = UnitStatus.Maintenance)
        val inventoryRepository = FakeInventoryRepository(
            items = listOf(RentalInFixtures.tentItem),
            units = listOf(maintenanceUnit),
        )

        val result = CheckSerializedAvailabilityUseCase(
            inventoryRepository = inventoryRepository,
            rentalRepository = FakeRentalRepository(),
        )(
            unitId = maintenanceUnit.id,
            dateRange = DateRange(LocalDate.of(2026, 5, 25), LocalDate.of(2026, 5, 26)),
        )

        assertFalse(result.isAvailable)
        assertEquals(AvailabilityBlockReason.UnavailableUnitStatus, result.reason)
    }

    @Test
    fun quantityAvailability_subtractsOverlappingReservedAndActiveQuantities() = runTest {
        val rental = RentalInFixtures.rental(
            status = RentalStatus.Active,
            lines = listOf(RentalInFixtures.quantityLine(quantity = 4)),
        )
        val inventoryRepository = FakeInventoryRepository(
            items = listOf(RentalInFixtures.trekkingPoleItem),
            quantityStocks = listOf(RentalInFixtures.trekkingPoleStock),
        )

        val result = CheckQuantityAvailabilityUseCase(
            inventoryRepository = inventoryRepository,
            rentalRepository = FakeRentalRepository(listOf(rental)),
        )(
            itemId = RentalInFixtures.trekkingPoleItem.id,
            dateRange = DateRange(LocalDate.of(2026, 5, 21), LocalDate.of(2026, 5, 23)),
        )

        assertEquals(6, result.totalQuantity)
        assertEquals(4, result.reservedOrActiveQuantity)
        assertEquals(2, result.availableQuantity)
        assertTrue(result.canReserve(2))
        assertFalse(result.canReserve(3))
    }

    @Test
    fun lifecycle_supportsPickupReturnAndCancel() {
        val lifecycle = RentalLifecycleUseCase()
        val reserved = RentalInFixtures.rental(status = RentalStatus.Reserved)
        val active = lifecycle.markPickedUp(
            rental = reserved,
            conditionBeforeHandoff = "Clean",
            amountPaid = MoneyAmount(150_000),
        )

        val returned = lifecycle.markReturned(
            rental = active,
            actualReturnDate = LocalDate.of(2026, 5, 22),
            conditionAfterReturn = "Good",
            refundAmount = MoneyAmount(100_000),
        )
        val cancelled = lifecycle.cancel(reserved)

        assertEquals(RentalStatus.Active, active.status)
        assertEquals(RentalStatus.Returned, returned.status)
        assertEquals(RentalStatus.Cancelled, cancelled.status)
    }

    @Test
    fun overdueAndDueToday_areDerivedFromCurrentDate() {
        val operationalState = RentalOperationalStateUseCase(currentDateProvider)
        val overdue = RentalInFixtures.rental(
            status = RentalStatus.Active,
            expectedReturnDate = LocalDate.of(2026, 5, 21),
        )
        val dueToday = RentalInFixtures.rental(
            status = RentalStatus.Active,
            expectedReturnDate = currentDate,
        )
        val reservedPastDue = RentalInFixtures.rental(
            status = RentalStatus.Reserved,
            expectedReturnDate = LocalDate.of(2026, 5, 21),
        )

        assertTrue(operationalState.isOverdue(overdue))
        assertTrue(operationalState.isDueToday(dueToday))
        assertFalse(operationalState.isOverdue(reservedPastDue))
    }

    @Test
    fun paymentSummary_coversExpectedStatuses() {
        val calculator = CalculatePaymentSummaryUseCase()

        assertEquals(PaymentStatus.Unpaid, calculator(RentalInFixtures.rental()).status)
        assertEquals(
            PaymentStatus.Partial,
            calculator(RentalInFixtures.rental(amountPaid = MoneyAmount(50_000))).status,
        )
        assertEquals(
            PaymentStatus.Paid,
            calculator(RentalInFixtures.rental(amountPaid = MoneyAmount(150_000))).status,
        )
        assertEquals(
            PaymentStatus.RefundDue,
            calculator(RentalInFixtures.rental(refundAmount = MoneyAmount(100_000))).status,
        )
        assertEquals(
            PaymentStatus.Refunded,
            calculator(
                RentalInFixtures.rental(
                    refundAmount = MoneyAmount(100_000),
                    isRefundCompleted = true,
                ),
            ).status,
        )
    }

    @Test
    fun dashboardSummary_surfacesAttentionLists() = runTest {
        val overdueRental = RentalInFixtures.rental(
            id = "overdue",
            status = RentalStatus.Active,
            expectedReturnDate = LocalDate.of(2026, 5, 21),
        )
        val dueTodayRental = RentalInFixtures.rental(
            id = "due-today",
            status = RentalStatus.Active,
            expectedReturnDate = currentDate,
            lines = listOf(
                RentalInFixtures.quantityLine(
                    rentalId = com.rentalin.core.model.RentalId("due-today"),
                    quantity = 6,
                ),
            ),
        )
        val repository = FakeDashboardRepository(
            DashboardData(
                rentals = listOf(overdueRental, dueTodayRental),
                units = listOf(
                    RentalInFixtures.tentUnit.copy(status = UnitStatus.Maintenance),
                    RentalInFixtures.tentUnit.copy(
                        id = com.rentalin.core.model.ItemUnitId("unit-lost"),
                        status = UnitStatus.Lost,
                    ),
                ),
                quantityStocks = listOf(RentalInFixtures.trekkingPoleStock),
                items = listOf(RentalInFixtures.tentItem, RentalInFixtures.trekkingPoleItem),
            ),
        )

        val summary = ObserveDashboardSummaryUseCase(
            dashboardRepository = repository,
            currentDateProvider = currentDateProvider,
        )().first()

        assertEquals(listOf(overdueRental, dueTodayRental), summary.activeRentals)
        assertEquals(listOf(overdueRental), summary.overdueRentals)
        assertEquals(listOf(dueTodayRental), summary.dueTodayRentals)
        assertEquals(2, summary.unpaidRentals.size)
        assertEquals(1, summary.fullyBookedStock.size)
        assertEquals(1, summary.maintenanceUnits.size)
        assertEquals(1, summary.lostUnits.size)
    }
}
