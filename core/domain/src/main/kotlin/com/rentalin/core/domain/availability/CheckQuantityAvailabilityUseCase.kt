package com.rentalin.core.domain.availability

import com.rentalin.core.common.repository.InventoryRepository
import com.rentalin.core.common.repository.RentalRepository
import com.rentalin.core.model.DateRange
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.QuantityAvailability
import com.rentalin.core.model.RentalId

class CheckQuantityAvailabilityUseCase(
    private val inventoryRepository: InventoryRepository,
    private val rentalRepository: RentalRepository,
) {
    suspend operator fun invoke(
        itemId: ItemId,
        dateRange: DateRange,
        excludedRentalId: RentalId? = null,
    ): QuantityAvailability {
        val stock = inventoryRepository.getStockForItem(itemId)
            ?: return QuantityAvailability(
                itemId = itemId,
                totalQuantity = 0,
                reservedOrActiveQuantity = 0,
                availableQuantity = 0,
            )
        val overlappingQuantity = rentalRepository.findOverlappingQuantityRentals(
            itemId = itemId,
            dateRange = dateRange,
            excludedRentalId = excludedRentalId,
        ).flatMap { it.lines }
            .filter { it.itemId == itemId && it.unitId == null }
            .sumOf { it.quantity }
        val availableQuantity = (stock.totalQuantity - overlappingQuantity).coerceAtLeast(0)

        return QuantityAvailability(
            itemId = itemId,
            totalQuantity = stock.totalQuantity,
            reservedOrActiveQuantity = overlappingQuantity,
            availableQuantity = availableQuantity,
        )
    }
}
