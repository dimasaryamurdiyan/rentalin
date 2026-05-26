package com.rentalin.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rentalin.core.database.dao.CustomerDao
import com.rentalin.core.database.dao.ItemDao
import com.rentalin.core.database.dao.ItemUnitDao
import com.rentalin.core.database.dao.QuantityStockDao
import com.rentalin.core.database.dao.RentalDao
import com.rentalin.core.database.model.CustomerEntity
import com.rentalin.core.database.model.ItemEntity
import com.rentalin.core.database.model.ItemUnitEntity
import com.rentalin.core.database.model.QuantityStockEntity
import com.rentalin.core.database.model.RentalEntity
import com.rentalin.core.database.model.RentalLineEntity

@Database(
    entities = [
        ItemEntity::class,
        ItemUnitEntity::class,
        QuantityStockEntity::class,
        CustomerEntity::class,
        RentalEntity::class,
        RentalLineEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class RentalInDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun itemUnitDao(): ItemUnitDao
    abstract fun quantityStockDao(): QuantityStockDao
    abstract fun customerDao(): CustomerDao
    abstract fun rentalDao(): RentalDao
}
