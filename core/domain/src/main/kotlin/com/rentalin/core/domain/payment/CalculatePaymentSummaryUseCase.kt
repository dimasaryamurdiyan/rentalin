package com.rentalin.core.domain.payment

import com.rentalin.core.model.MoneyAmount
import com.rentalin.core.model.PaymentStatus
import com.rentalin.core.model.PaymentSummary
import com.rentalin.core.model.Rental

class CalculatePaymentSummaryUseCase {
    operator fun invoke(rental: Rental): PaymentSummary {
        val subtotal = rental.rentalFee + rental.extraCharges
        val balanceDue = subtotal.minusOrZero(rental.amountPaid)
        val refundDue = if (rental.isRefundCompleted) {
            MoneyAmount.Zero
        } else {
            rental.refundAmount
        }
        val status = when {
            rental.refundAmount.minorUnits > 0 && rental.isRefundCompleted -> PaymentStatus.Refunded
            rental.refundAmount.minorUnits > 0 -> PaymentStatus.RefundDue
            rental.amountPaid.minorUnits == 0L && subtotal.minorUnits > 0L -> PaymentStatus.Unpaid
            rental.amountPaid < subtotal -> PaymentStatus.Partial
            else -> PaymentStatus.Paid
        }

        return PaymentSummary(
            subtotal = subtotal,
            paidTowardCharges = rental.amountPaid,
            balanceDue = balanceDue,
            refundDue = refundDue,
            status = status,
        )
    }
}
