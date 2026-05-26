package com.rentalin.core.common.repository

import com.rentalin.core.model.Item
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnit
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.QuantityStock
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    fun observeItems(): Flow<List<Item>>
    fun observeActiveItems(): Flow<List<Item>>
    fun observeUnits(): Flow<List<ItemUnit>>
    fun observeUnitsForItem(itemId: ItemId): Flow<List<ItemUnit>>
    fun observeQuantityStocks(): Flow<List<QuantityStock>>
    suspend fun getItem(itemId: ItemId): Item?
    suspend fun getUnit(unitId: ItemUnitId): ItemUnit?
    suspend fun getStockForItem(itemId: ItemId): QuantityStock?
    suspend fun saveItem(item: Item)
    suspend fun saveUnit(unit: ItemUnit)
    suspend fun saveQuantityStock(stock: QuantityStock)
}
