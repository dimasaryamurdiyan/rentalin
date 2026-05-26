package com.rentalin.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rentalin.core.database.model.ItemUnitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemUnitDao {
    @Query("SELECT * FROM item_units ORDER BY code")
    fun observeUnits(): Flow<List<ItemUnitEntity>>

    @Query("SELECT * FROM item_units WHERE item_id = :itemId ORDER BY code")
    fun observeUnitsForItem(itemId: String): Flow<List<ItemUnitEntity>>

    @Query("SELECT * FROM item_units WHERE id = :id")
    suspend fun getUnit(id: String): ItemUnitEntity?

    @Upsert
    suspend fun upsertUnit(unit: ItemUnitEntity)
}
