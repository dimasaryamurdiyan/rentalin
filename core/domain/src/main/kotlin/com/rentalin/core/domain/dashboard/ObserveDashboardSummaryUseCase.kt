package com.rentalin.core.domain.dashboard

import com.rentalin.core.domain.repository.DashboardRepository
import com.rentalin.core.domain.payment.CalculatePaymentSummaryUseCase
import com.rentalin.core.domain.rental.RentalOperationalStateUseCase
import com.rentalin.core.domain.time.CurrentDateProvider
import com.rentalin.core.model.DashboardSummary
import com.rentalin.core.model.DateRange
import com.rentalin.core.model.QuantityAvailability
import com.rentalin.core.model.RentalStatus
import com.rentalin.core.model.UnitStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveDashboardSummaryUseCase(
    private val dashboardRepository: DashboardRepository,
    private val currentDateProvider: CurrentDateProvider,
    private val paymentSummaryUseCase: CalculatePaymentSummaryUseCase = CalculatePaymentSummaryUseCase(),
) {
    operator fun invoke(): Flow<DashboardSummary> {
        val rentalState = RentalOperationalStateUseCase(currentDateProvider)

        return dashboardRepository.observeDashboardData().map { data ->
            val activeRentals = data.rentals.filter { it.status == RentalStatus.Active }
            val currentRange = DateRange(
                startDate = currentDateProvider.currentDate(),
                expectedReturnDate = currentDateProvider.currentDate(),
            )
            val stockAvailability = data.quantityStocks.map { stock ->
                val reservedOrActiveQuantity = data.rentals
                    .filter { it.status == RentalStatus.Reserved || it.status == RentalStatus.Active }
                    .filter { it.dateRange.overlaps(currentRange) }
                    .flatMap { it.lines }
                    .filter { it.itemId == stock.itemId && it.unitId == null }
                    .sumOf { it.quantity }
                QuantityAvailability(
                    itemId = stock.itemId,
                    totalQuantity = stock.totalQuantity,
                    reservedOrActiveQuantity = reservedOrActiveQuantity,
                    availableQuantity = (stock.totalQuantity - reservedOrActiveQuantity).coerceAtLeast(0),
                )
            }

            DashboardSummary(
                activeRentals = activeRentals,
                overdueRentals = data.rentals.filter { rentalState.isOverdue(it) },
                dueTodayRentals = data.rentals.filter { rentalState.isDueToday(it) },
                unpaidRentals = data.rentals.filter {
                    it.status != RentalStatus.Cancelled &&
                        paymentSummaryUseCase(it).balanceDue.minorUnits > 0
                },
                lowStock = stockAvailability.filter { availability ->
                    val threshold = data.quantityStocks
                        .firstOrNull { it.itemId == availability.itemId }
                        ?.lowStockThreshold
                    threshold != null && availability.availableQuantity in 1..threshold
                },
                fullyBookedStock = stockAvailability.filter { it.availableQuantity == 0 },
                maintenanceUnits = data.units.filter { it.status == UnitStatus.Maintenance },
                lostUnits = data.units.filter { it.status == UnitStatus.Lost },
            )
        }
    }
}
