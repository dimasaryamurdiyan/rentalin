package com.rentalin.core.model

import java.time.LocalDate
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DateRangeTest {
    @Test
    fun overlappingRanges_returnTrue() {
        val first = DateRange(LocalDate.of(2026, 5, 20), LocalDate.of(2026, 5, 22))
        val second = DateRange(LocalDate.of(2026, 5, 22), LocalDate.of(2026, 5, 24))

        assertTrue(first.overlaps(second))
    }

    @Test
    fun nonOverlappingRanges_returnFalse() {
        val first = DateRange(LocalDate.of(2026, 5, 20), LocalDate.of(2026, 5, 22))
        val second = DateRange(LocalDate.of(2026, 5, 23), LocalDate.of(2026, 5, 24))

        assertFalse(first.overlaps(second))
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidDateRange_throws() {
        DateRange(LocalDate.of(2026, 5, 22), LocalDate.of(2026, 5, 20))
    }
}
