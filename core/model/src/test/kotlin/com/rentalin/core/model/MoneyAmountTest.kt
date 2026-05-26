package com.rentalin.core.model

import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyAmountTest {
    @Test(expected = IllegalArgumentException::class)
    fun negativeMoney_throws() {
        MoneyAmount(-1)
    }

    @Test
    fun minusOrZero_neverReturnsNegative() {
        assertEquals(MoneyAmount.Zero, MoneyAmount(10).minusOrZero(MoneyAmount(20)))
    }
}
