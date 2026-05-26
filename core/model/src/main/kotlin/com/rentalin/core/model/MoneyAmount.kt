package com.rentalin.core.model

data class MoneyAmount(val minorUnits: Long) : Comparable<MoneyAmount> {
    init {
        require(minorUnits >= 0) { "MoneyAmount cannot be negative." }
    }

    override fun compareTo(other: MoneyAmount): Int = minorUnits.compareTo(other.minorUnits)

    operator fun plus(other: MoneyAmount): MoneyAmount = MoneyAmount(minorUnits + other.minorUnits)

    fun minusOrZero(other: MoneyAmount): MoneyAmount =
        MoneyAmount((minorUnits - other.minorUnits).coerceAtLeast(0))

    companion object {
        val Zero = MoneyAmount(0)
    }
}
