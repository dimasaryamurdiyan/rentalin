package com.rentalin.core.common.repository

import com.rentalin.core.model.DateRange
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.Rental
import com.rentalin.core.model.RentalId
import com.rentalin.core.model.RentalValidationError
import kotlinx.coroutines.flow.Flow

interface RentalRepository {
    fun observeRentals(): Flow<List<Rental>>
    suspend fun getRental(rentalId: RentalId): Rental?
    suspend fun saveRental(rental: Rental): List<RentalValidationError>
    suspend fun findOverlappingRentalsForUnit(
        unitId: ItemUnitId,
        dateRange: DateRange,
        excludedRentalId: RentalId? = null,
    ): List<Rental>
    suspend fun findOverlappingQuantityRentals(
        itemId: ItemId,
        dateRange: DateRange,
        excludedRentalId: RentalId? = null,
    ): List<Rental>
}
