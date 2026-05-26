package com.rentalin.core.model

data class Item(
    val id: ItemId,
    val name: String,
    val category: String,
    val rentalUnitType: RentalUnitType,
    val defaultPrice: MoneyAmount,
    val notes: String,
    val isArchived: Boolean,
)

enum class RentalUnitType {
    Serialized,
    Quantity,
}

data class ItemUnit(
    val id: ItemUnitId,
    val itemId: ItemId,
    val code: String,
    val condition: String,
    val status: UnitStatus,
    val notes: String,
)

enum class UnitStatus {
    Available,
    Reserved,
    Rented,
    Maintenance,
    Lost,
    Archived,
}

data class QuantityStock(
    val id: QuantityStockId,
    val itemId: ItemId,
    val totalQuantity: Int,
    val lowStockThreshold: Int?,
    val notes: String,
)
