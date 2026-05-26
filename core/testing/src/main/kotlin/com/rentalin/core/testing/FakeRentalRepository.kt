package com.rentalin.core.testing

import com.rentalin.core.common.repository.RentalRepository
import com.rentalin.core.model.DateRange
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.Rental
import com.rentalin.core.model.RentalId
import com.rentalin.core.model.RentalStatus
import com.rentalin.core.model.RentalValidationError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRentalRepository(
    rentals: List<Rental> = emptyList(),
) : RentalRepository {
    private val rentalsFlow = MutableStateFlow(rentals)

    override fun observeRentals(): Flow<List<Rental>> = rentalsFlow

    override suspend fun getRental(rentalId: RentalId): Rental? =
        rentalsFlow.value.firstOrNull { it.id == rentalId }

    override suspend fun saveRental(rental: Rental): List<RentalValidationError> {
        rentalsFlow.value = rentalsFlow.value.upsert(rental) { it.id == rental.id }
        return emptyList()
    }

    override suspend fun findOverlappingRentalsForUnit(
        unitId: ItemUnitId,
        dateRange: DateRange,
        excludedRentalId: RentalId?,
    ): List<Rental> = rentalsFlow.value.filter { rental ->
        rental.id != excludedRentalId &&
            rental.status in blockingStatuses &&
            rental.dateRange.overlaps(dateRange) &&
            rental.lines.any { it.unitId == unitId }
    }

    override suspend fun findOverlappingQuantityRentals(
        itemId: ItemId,
        dateRange: DateRange,
        excludedRentalId: RentalId?,
    ): List<Rental> = rentalsFlow.value.filter { rental ->
        rental.id != excludedRentalId &&
            rental.status in blockingStatuses &&
            rental.dateRange.overlaps(dateRange) &&
            rental.lines.any { it.itemId == itemId && it.unitId == null }
    }

    private companion object {
        val blockingStatuses = setOf(RentalStatus.Reserved, RentalStatus.Active)
    }
}
