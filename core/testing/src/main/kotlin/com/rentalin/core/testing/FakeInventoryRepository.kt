package com.rentalin.core.testing

import com.rentalin.core.common.repository.InventoryRepository
import com.rentalin.core.model.Item
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnit
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.QuantityStock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeInventoryRepository(
    items: List<Item> = emptyList(),
    units: List<ItemUnit> = emptyList(),
    quantityStocks: List<QuantityStock> = emptyList(),
) : InventoryRepository {
    private val itemsFlow = MutableStateFlow(items)
    private val unitsFlow = MutableStateFlow(units)
    private val quantityStocksFlow = MutableStateFlow(quantityStocks)

    override fun observeItems(): Flow<List<Item>> = itemsFlow

    override fun observeActiveItems(): Flow<List<Item>> =
        itemsFlow.map { items -> items.filterNot { it.isArchived } }

    override fun observeUnits(): Flow<List<ItemUnit>> = unitsFlow

    override fun observeUnitsForItem(itemId: ItemId): Flow<List<ItemUnit>> =
        unitsFlow.map { units -> units.filter { it.itemId == itemId } }

    override fun observeQuantityStocks(): Flow<List<QuantityStock>> = quantityStocksFlow

    override suspend fun getItem(itemId: ItemId): Item? =
        itemsFlow.value.firstOrNull { it.id == itemId }

    override suspend fun getUnit(unitId: ItemUnitId): ItemUnit? =
        unitsFlow.value.firstOrNull { it.id == unitId }

    override suspend fun getStockForItem(itemId: ItemId): QuantityStock? =
        quantityStocksFlow.value.firstOrNull { it.itemId == itemId }

    override suspend fun saveItem(item: Item) {
        itemsFlow.value = itemsFlow.value.upsert(item) { it.id == item.id }
    }

    override suspend fun saveUnit(unit: ItemUnit) {
        unitsFlow.value = unitsFlow.value.upsert(unit) { it.id == unit.id }
    }

    override suspend fun saveQuantityStock(stock: QuantityStock) {
        quantityStocksFlow.value = quantityStocksFlow.value.upsert(stock) { it.id == stock.id }
    }
}
