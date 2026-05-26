package com.rentalin.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "items",
    indices = [
        Index("category"),
        Index("is_archived"),
    ],
)
data class ItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    @ColumnInfo(name = "rental_unit_type") val rentalUnitType: String,
    @ColumnInfo(name = "default_price_minor_units") val defaultPriceMinorUnits: Long,
    val notes: String,
    @ColumnInfo(name = "is_archived") val isArchived: Boolean,
)
