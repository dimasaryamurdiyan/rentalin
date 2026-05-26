package com.rentalin.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rentalin.core.database.model.QuantityStockEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuantityStockDao {
    @Query("SELECT * FROM quantity_stocks ORDER BY item_id")
    fun observeQuantityStocks(): Flow<List<QuantityStockEntity>>

    @Query("SELECT * FROM quantity_stocks WHERE item_id = :itemId")
    suspend fun getStockForItem(itemId: String): QuantityStockEntity?

    @Upsert
    suspend fun upsertStock(stock: QuantityStockEntity)
}
