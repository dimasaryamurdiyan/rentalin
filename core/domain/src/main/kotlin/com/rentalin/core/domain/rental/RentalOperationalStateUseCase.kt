package com.rentalin.core.domain.rental

import com.rentalin.core.domain.time.CurrentDateProvider
import com.rentalin.core.model.Rental
import com.rentalin.core.model.RentalStatus

class RentalOperationalStateUseCase(
    private val currentDateProvider: CurrentDateProvider,
) {
    fun isOverdue(rental: Rental): Boolean =
        rental.status == RentalStatus.Active &&
            rental.expectedReturnDate.isBefore(currentDateProvider.currentDate())

    fun isDueToday(rental: Rental): Boolean =
        rental.status == RentalStatus.Active &&
            rental.expectedReturnDate == currentDateProvider.currentDate()
}
