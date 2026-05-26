package com.rentalin.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customers",
    indices = [
        Index("name"),
        Index("phone"),
    ],
)
data class CustomerEntity(
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    @ColumnInfo(name = "identity_number") val identityNumber: String?,
    val address: String?,
    val notes: String,
)
