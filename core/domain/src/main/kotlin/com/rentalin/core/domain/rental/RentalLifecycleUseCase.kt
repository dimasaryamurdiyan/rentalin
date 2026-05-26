package com.rentalin.core.domain.rental

import com.rentalin.core.model.MoneyAmount
import com.rentalin.core.model.Rental
import com.rentalin.core.model.RentalStatus
import java.time.LocalDate

class RentalLifecycleUseCase {
    fun markPickedUp(
        rental: Rental,
        conditionBeforeHandoff: String,
        depositAmount: MoneyAmount = rental.depositAmount,
        amountPaid: MoneyAmount = rental.amountPaid,
        pickupNotes: String = rental.pickupNotes,
    ): Rental {
        require(rental.status == RentalStatus.Reserved) {
            "Only reserved rentals can be marked active."
        }
        return rental.copy(
            status = RentalStatus.Active,
            conditionBeforeHandoff = conditionBeforeHandoff,
            depositAmount = depositAmount,
            amountPaid = amountPaid,
            pickupNotes = pickupNotes,
        )
    }

    fun markReturned(
        rental: Rental,
        actualReturnDate: LocalDate,
        conditionAfterReturn: String,
        extraCharges: MoneyAmount = rental.extraCharges,
        refundAmount: MoneyAmount = rental.refundAmount,
        isRefundCompleted: Boolean = rental.isRefundCompleted,
        returnNotes: String = rental.returnNotes,
    ): Rental {
        require(rental.status == RentalStatus.Active) {
            "Only active rentals can be returned."
        }
        return rental.copy(
            status = RentalStatus.Returned,
            actualReturnDate = actualReturnDate,
            conditionAfterReturn = conditionAfterReturn,
            extraCharges = extraCharges,
            refundAmount = refundAmount,
            isRefundCompleted = isRefundCompleted,
            returnNotes = returnNotes,
        )
    }

    fun cancel(rental: Rental): Rental {
        require(rental.status == RentalStatus.Reserved || rental.status == RentalStatus.Active) {
            "Only reserved or active rentals can be cancelled."
        }
        return rental.copy(status = RentalStatus.Cancelled)
    }
}
