package com.rentalin.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rentalin.core.database.model.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY name")
    fun observeItems(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE is_archived = 0 ORDER BY name")
    fun observeActiveItems(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItem(id: String): ItemEntity?

    @Upsert
    suspend fun upsertItem(item: ItemEntity)
}
