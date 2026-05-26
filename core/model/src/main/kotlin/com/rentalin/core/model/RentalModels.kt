package com.rentalin.core.model

import java.time.LocalDate

data class Rental(
    val id: RentalId,
    val customer: RentalCustomerSnapshot,
    val startDate: LocalDate,
    val expectedReturnDate: LocalDate,
    val actualReturnDate: LocalDate?,
    val status: RentalStatus,
    val lines: List<RentalLine>,
    val rentalFee: MoneyAmount,
    val depositAmount: MoneyAmount,
    val amountPaid: MoneyAmount,
    val extraCharges: MoneyAmount,
    val refundAmount: MoneyAmount,
    val isRefundCompleted: Boolean,
    val conditionBeforeHandoff: String,
    val conditionAfterReturn: String,
    val pickupNotes: String,
    val returnNotes: String,
    val generalNotes: String,
) {
    val dateRange: DateRange
        get() = DateRange(startDate, expectedReturnDate)
}

enum class RentalStatus {
    Reserved,
    Active,
    Returned,
    Cancelled,
}

data class RentalLine(
    val id: RentalLineId,
    val rentalId: RentalId,
    val itemId: ItemId,
    val unitId: ItemUnitId?,
    val quantity: Int,
    val price: MoneyAmount,
    val conditionNotes: String,
)

enum class PaymentStatus {
    Unpaid,
    Partial,
    Paid,
    RefundDue,
    Refunded,
}
