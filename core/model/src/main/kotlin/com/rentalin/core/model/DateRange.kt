package com.rentalin.core.model

import java.time.LocalDate

data class DateRange(
    val startDate: LocalDate,
    val expectedReturnDate: LocalDate,
) {
    init {
        require(!expectedReturnDate.isBefore(startDate)) {
            "Expected return date cannot be before start date."
        }
    }

    fun overlaps(other: DateRange): Boolean =
        !startDate.isAfter(other.expectedReturnDate) &&
            !other.startDate.isAfter(expectedReturnDate)
}
