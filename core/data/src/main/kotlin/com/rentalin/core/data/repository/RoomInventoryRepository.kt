package com.rentalin.core.data.repository

import com.rentalin.core.common.repository.InventoryRepository
import com.rentalin.core.data.model.asEntity
import com.rentalin.core.data.model.asModel
import com.rentalin.core.database.dao.ItemDao
import com.rentalin.core.database.dao.ItemUnitDao
import com.rentalin.core.database.dao.QuantityStockDao
import com.rentalin.core.model.Item
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnit
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.QuantityStock
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomInventoryRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val itemUnitDao: ItemUnitDao,
    private val quantityStockDao: QuantityStockDao,
) : InventoryRepository {
    override fun observeItems(): Flow<List<Item>> =
        itemDao.observeItems().map { items -> items.map { it.asModel() } }

    override fun observeActiveItems(): Flow<List<Item>> =
        itemDao.observeActiveItems().map { items -> items.map { it.asModel() } }

    override fun observeUnits(): Flow<List<ItemUnit>> =
        itemUnitDao.observeUnits().map { units -> units.map { it.asModel() } }

    override fun observeUnitsForItem(itemId: ItemId): Flow<List<ItemUnit>> =
        itemUnitDao.observeUnitsForItem(itemId.value).map { units -> units.map { it.asModel() } }

    override fun observeQuantityStocks(): Flow<List<QuantityStock>> =
        quantityStockDao.observeQuantityStocks().map { stocks -> stocks.map { it.asModel() } }

    override suspend fun getItem(itemId: ItemId): Item? =
        itemDao.getItem(itemId.value)?.asModel()

    override suspend fun getUnit(unitId: ItemUnitId): ItemUnit? =
        itemUnitDao.getUnit(unitId.value)?.asModel()

    override suspend fun getStockForItem(itemId: ItemId): QuantityStock? =
        quantityStockDao.getStockForItem(itemId.value)?.asModel()

    override suspend fun saveItem(item: Item) {
        itemDao.upsertItem(item.asEntity())
    }

    override suspend fun saveUnit(unit: ItemUnit) {
        itemUnitDao.upsertUnit(unit.asEntity())
    }

    override suspend fun saveQuantityStock(stock: QuantityStock) {
        require(stock.totalQuantity >= 0) { "Quantity stock cannot be negative." }
        val lowStockThreshold = stock.lowStockThreshold
        require(lowStockThreshold == null || lowStockThreshold >= 0) {
            "Low stock threshold cannot be negative."
        }
        quantityStockDao.upsertStock(stock.asEntity())
    }
}
