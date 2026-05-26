package com.rentalin.core.data.repository

import com.rentalin.core.common.repository.DashboardData
import com.rentalin.core.common.repository.DashboardRepository
import com.rentalin.core.common.repository.InventoryRepository
import com.rentalin.core.common.repository.RentalRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class RoomDashboardRepository @Inject constructor(
    private val rentalRepository: RentalRepository,
    private val inventoryRepository: InventoryRepository,
) : DashboardRepository {
    override fun observeDashboardData(): Flow<DashboardData> = combine(
        rentalRepository.observeRentals(),
        inventoryRepository.observeUnits(),
        inventoryRepository.observeQuantityStocks(),
        inventoryRepository.observeItems(),
    ) { rentals, units, quantityStocks, items ->
        DashboardData(
            rentals = rentals,
            units = units,
            quantityStocks = quantityStocks,
            items = items,
        )
    }
}
