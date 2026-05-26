package com.rentalin.core.domain.validation

import com.rentalin.core.model.RentalValidationError
import java.time.LocalDate

class ValidateDateRangeUseCase {
    operator fun invoke(startDate: LocalDate, expectedReturnDate: LocalDate): RentalValidationError? =
        if (expectedReturnDate.isBefore(startDate)) {
            RentalValidationError.InvalidDateRange
        } else {
            null
        }
}
