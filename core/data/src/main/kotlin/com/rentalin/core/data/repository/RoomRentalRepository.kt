package com.rentalin.core.data.repository

import com.rentalin.core.common.repository.RentalRepository
import com.rentalin.core.data.model.asEntity
import com.rentalin.core.data.model.asModel
import com.rentalin.core.database.dao.RentalDao
import com.rentalin.core.model.DateRange
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.Rental
import com.rentalin.core.model.RentalId
import com.rentalin.core.model.RentalStatus
import com.rentalin.core.model.RentalValidationError
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomRentalRepository @Inject constructor(
    private val rentalDao: RentalDao,
) : RentalRepository {
    override fun observeRentals(): Flow<List<Rental>> =
        rentalDao.observeRentals().map { rentals -> rentals.map { it.asModel() } }

    override suspend fun getRental(rentalId: RentalId): Rental? =
        rentalDao.getRental(rentalId.value)?.asModel()

    override suspend fun saveRental(rental: Rental): List<RentalValidationError> {
        val errors = rental.validationErrors()
        if (errors.isNotEmpty()) return errors
        rentalDao.replaceRental(
            rental = rental.asEntity(),
            lines = rental.lines.map { it.asEntity() },
        )
        return emptyList()
    }

    override suspend fun findOverlappingRentalsForUnit(
        unitId: ItemUnitId,
        dateRange: DateRange,
        excludedRentalId: RentalId?,
    ): List<Rental> = rentalDao.getOverlappingRentalsForUnit(
        unitId = unitId.value,
        startDate = dateRange.startDate.toString(),
        expectedReturnDate = dateRange.expectedReturnDate.toString(),
        blockingStatuses = blockingStatuses,
        excludedRentalId = excludedRentalId?.value ?: "",
    ).map { it.asModel() }

    override suspend fun findOverlappingQuantityRentals(
        itemId: ItemId,
        dateRange: DateRange,
        excludedRentalId: RentalId?,
    ): List<Rental> = rentalDao.getOverlappingQuantityRentals(
        itemId = itemId.value,
        startDate = dateRange.startDate.toString(),
        expectedReturnDate = dateRange.expectedReturnDate.toString(),
        blockingStatuses = blockingStatuses,
        excludedRentalId = excludedRentalId?.value ?: "",
    ).map { it.asModel() }

    private fun Rental.validationErrors(): List<RentalValidationError> = buildList {
        if (expectedReturnDate.isBefore(startDate)) add(RentalValidationError.InvalidDateRange)
        if (customer.name.isBlank()) add(RentalValidationError.MissingCustomerName)
        if (customer.phone.isBlank()) add(RentalValidationError.MissingCustomerPhone)
        if (lines.any { it.quantity <= 0 }) add(RentalValidationError.InvalidQuantity)
    }

    private companion object {
        val blockingStatuses = listOf(RentalStatus.Reserved.name, RentalStatus.Active.name)
    }
}
