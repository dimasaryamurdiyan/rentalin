package com.rentalin.core.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Database(
    entities = [RentalInDatabaseMarker::class],
    version = 1,
    exportSchema = false,
)
abstract class RentalInDatabase : RoomDatabase()

@Entity(tableName = "database_marker")
internal data class RentalInDatabaseMarker(
    @PrimaryKey val id: Int,
)
