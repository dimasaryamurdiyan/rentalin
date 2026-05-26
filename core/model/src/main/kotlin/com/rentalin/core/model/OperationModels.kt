package com.rentalin.core.model

data class SerializedAvailability(
    val unitId: ItemUnitId,
    val isAvailable: Boolean,
    val blockingRentalIds: List<RentalId>,
    val reason: AvailabilityBlockReason?,
)

data class QuantityAvailability(
    val itemId: ItemId,
    val totalQuantity: Int,
    val reservedOrActiveQuantity: Int,
    val availableQuantity: Int,
) {
    fun canReserve(requestedQuantity: Int): Boolean = requestedQuantity in 1..availableQuantity
}

enum class AvailabilityBlockReason {
    ArchivedItem,
    UnavailableUnitStatus,
    OverlappingRental,
    InsufficientQuantity,
    InvalidQuantity,
}

sealed interface RentalValidationError {
    data object InvalidDateRange : RentalValidationError
    data object NegativeMoney : RentalValidationError
    data object InvalidQuantity : RentalValidationError
    data object MissingCustomerName : RentalValidationError
    data object MissingCustomerPhone : RentalValidationError
}

data class PaymentSummary(
    val subtotal: MoneyAmount,
    val paidTowardCharges: MoneyAmount,
    val balanceDue: MoneyAmount,
    val refundDue: MoneyAmount,
    val status: PaymentStatus,
)

data class DashboardSummary(
    val activeRentals: List<Rental>,
    val overdueRentals: List<Rental>,
    val dueTodayRentals: List<Rental>,
    val unpaidRentals: List<Rental>,
    val lowStock: List<QuantityAvailability>,
    val fullyBookedStock: List<QuantityAvailability>,
    val maintenanceUnits: List<ItemUnit>,
    val lostUnits: List<ItemUnit>,
)
