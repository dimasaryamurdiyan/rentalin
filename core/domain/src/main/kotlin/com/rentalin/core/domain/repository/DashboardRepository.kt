package com.rentalin.core.domain.repository

import com.rentalin.core.model.Item
import com.rentalin.core.model.ItemUnit
import com.rentalin.core.model.QuantityStock
import com.rentalin.core.model.Rental
import kotlinx.coroutines.flow.Flow

data class DashboardData(
    val rentals: List<Rental>,
    val units: List<ItemUnit>,
    val quantityStocks: List<QuantityStock>,
    val items: List<Item>,
)

interface DashboardRepository {
    fun observeDashboardData(): Flow<DashboardData>
}
