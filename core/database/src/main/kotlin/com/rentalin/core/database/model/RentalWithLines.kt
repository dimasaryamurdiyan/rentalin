package com.rentalin.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class RentalWithLines(
    @Embedded val rental: RentalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "rental_id",
    )
    val lines: List<RentalLineEntity>,
)
