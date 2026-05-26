package com.rentalin.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rentals",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customer_id"],
            onDelete = ForeignKey.SET_NULL,
        ),
    ],
    indices = [
        Index("status"),
        Index("start_date"),
        Index("expected_return_date"),
        Index("customer_id"),
    ],
)
data class RentalEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "customer_id") val customerId: String?,
    @ColumnInfo(name = "customer_name") val customerName: String,
    @ColumnInfo(name = "customer_phone") val customerPhone: String,
    @ColumnInfo(name = "customer_identity_number") val customerIdentityNumber: String?,
    @ColumnInfo(name = "customer_address") val customerAddress: String?,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "expected_return_date") val expectedReturnDate: String,
    @ColumnInfo(name = "actual_return_date") val actualReturnDate: String?,
    val status: String,
    @ColumnInfo(name = "rental_fee_minor_units") val rentalFeeMinorUnits: Long,
    @ColumnInfo(name = "deposit_amount_minor_units") val depositAmountMinorUnits: Long,
    @ColumnInfo(name = "amount_paid_minor_units") val amountPaidMinorUnits: Long,
    @ColumnInfo(name = "extra_charges_minor_units") val extraChargesMinorUnits: Long,
    @ColumnInfo(name = "refund_amount_minor_units") val refundAmountMinorUnits: Long,
    @ColumnInfo(name = "is_refund_completed") val isRefundCompleted: Boolean,
    @ColumnInfo(name = "condition_before_handoff") val conditionBeforeHandoff: String,
    @ColumnInfo(name = "condition_after_return") val conditionAfterReturn: String,
    @ColumnInfo(name = "pickup_notes") val pickupNotes: String,
    @ColumnInfo(name = "return_notes") val returnNotes: String,
    @ColumnInfo(name = "general_notes") val generalNotes: String,
)
