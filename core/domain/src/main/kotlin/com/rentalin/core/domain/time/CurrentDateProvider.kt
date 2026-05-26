package com.rentalin.core.domain.time

import java.time.LocalDate

fun interface CurrentDateProvider {
    fun currentDate(): LocalDate
}

object SystemCurrentDateProvider : CurrentDateProvider {
    override fun currentDate(): LocalDate = LocalDate.now()
}
