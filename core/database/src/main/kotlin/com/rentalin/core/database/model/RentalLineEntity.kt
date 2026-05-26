package com.rentalin.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rental_lines",
    foreignKeys = [
        ForeignKey(
            entity = RentalEntity::class,
            parentColumns = ["id"],
            childColumns = ["rental_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
        ForeignKey(
            entity = ItemUnitEntity::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
    ],
    indices = [
        Index("rental_id"),
        Index("item_id"),
        Index("unit_id"),
    ],
)
data class RentalLineEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "rental_id") val rentalId: String,
    @ColumnInfo(name = "item_id") val itemId: String,
    @ColumnInfo(name = "unit_id") val unitId: String?,
    val quantity: Int,
    @ColumnInfo(name = "price_minor_units") val priceMinorUnits: Long,
    @ColumnInfo(name = "condition_notes") val conditionNotes: String,
)
