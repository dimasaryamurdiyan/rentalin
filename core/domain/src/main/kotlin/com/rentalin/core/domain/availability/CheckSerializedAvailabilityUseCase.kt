package com.rentalin.core.domain.availability

import com.rentalin.core.common.repository.InventoryRepository
import com.rentalin.core.common.repository.RentalRepository
import com.rentalin.core.model.AvailabilityBlockReason
import com.rentalin.core.model.DateRange
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.RentalId
import com.rentalin.core.model.SerializedAvailability
import com.rentalin.core.model.UnitStatus

class CheckSerializedAvailabilityUseCase(
    private val inventoryRepository: InventoryRepository,
    private val rentalRepository: RentalRepository,
) {
    suspend operator fun invoke(
        unitId: ItemUnitId,
        dateRange: DateRange,
        excludedRentalId: RentalId? = null,
    ): SerializedAvailability {
        val unit = inventoryRepository.getUnit(unitId)
            ?: return blocked(unitId, AvailabilityBlockReason.UnavailableUnitStatus)
        val item = inventoryRepository.getItem(unit.itemId)
        if (item?.isArchived == true) {
            return blocked(unitId, AvailabilityBlockReason.ArchivedItem)
        }
        if (unit.status in unavailableStatuses) {
            return blocked(unitId, AvailabilityBlockReason.UnavailableUnitStatus)
        }

        val overlappingRentals = rentalRepository.findOverlappingRentalsForUnit(
            unitId = unitId,
            dateRange = dateRange,
            excludedRentalId = excludedRentalId,
        )
        if (overlappingRentals.isNotEmpty()) {
            return SerializedAvailability(
                unitId = unitId,
                isAvailable = false,
                blockingRentalIds = overlappingRentals.map { it.id },
                reason = AvailabilityBlockReason.OverlappingRental,
            )
        }

        return SerializedAvailability(
            unitId = unitId,
            isAvailable = true,
            blockingRentalIds = emptyList(),
            reason = null,
        )
    }

    private fun blocked(
        unitId: ItemUnitId,
        reason: AvailabilityBlockReason,
    ): SerializedAvailability = SerializedAvailability(
        unitId = unitId,
        isAvailable = false,
        blockingRentalIds = emptyList(),
        reason = reason,
    )

    private companion object {
        val unavailableStatuses = setOf(
            UnitStatus.Maintenance,
            UnitStatus.Lost,
            UnitStatus.Archived,
        )
    }
}
