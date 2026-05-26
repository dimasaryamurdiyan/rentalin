package com.rentalin.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.rentalin.core.database.model.RentalEntity
import com.rentalin.core.database.model.RentalLineEntity
import com.rentalin.core.database.model.RentalWithLines
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RentalDao {
    @Transaction
    @Query("SELECT * FROM rentals ORDER BY expected_return_date, start_date")
    abstract fun observeRentals(): Flow<List<RentalWithLines>>

    @Transaction
    @Query("SELECT * FROM rentals WHERE status IN (:statuses) ORDER BY expected_return_date, start_date")
    abstract fun observeRentalsByStatus(statuses: List<String>): Flow<List<RentalWithLines>>

    @Transaction
    @Query("SELECT * FROM rentals WHERE id = :id")
    abstract suspend fun getRental(id: String): RentalWithLines?

    @Transaction
    @Query(
        """
        SELECT DISTINCT rentals.* FROM rentals
        INNER JOIN rental_lines ON rentals.id = rental_lines.rental_id
        WHERE rental_lines.unit_id = :unitId
        AND rentals.status IN (:blockingStatuses)
        AND rentals.id != :excludedRentalId
        AND rentals.start_date <= :expectedReturnDate
        AND :startDate <= rentals.expected_return_date
        """
    )
    abstract suspend fun getOverlappingRentalsForUnit(
        unitId: String,
        startDate: String,
        expectedReturnDate: String,
        blockingStatuses: List<String>,
        excludedRentalId: String,
    ): List<RentalWithLines>

    @Transaction
    @Query(
        """
        SELECT DISTINCT rentals.* FROM rentals
        INNER JOIN rental_lines ON rentals.id = rental_lines.rental_id
        WHERE rental_lines.item_id = :itemId
        AND rental_lines.unit_id IS NULL
        AND rentals.status IN (:blockingStatuses)
        AND rentals.id != :excludedRentalId
        AND rentals.start_date <= :expectedReturnDate
        AND :startDate <= rentals.expected_return_date
        """
    )
    abstract suspend fun getOverlappingQuantityRentals(
        itemId: String,
        startDate: String,
        expectedReturnDate: String,
        blockingStatuses: List<String>,
        excludedRentalId: String,
    ): List<RentalWithLines>

    @Upsert
    protected abstract suspend fun upsertRental(rental: RentalEntity)

    @Upsert
    protected abstract suspend fun upsertLines(lines: List<RentalLineEntity>)

    @Query("DELETE FROM rental_lines WHERE rental_id = :rentalId")
    protected abstract suspend fun deleteLinesForRental(rentalId: String)

    @Transaction
    open suspend fun replaceRental(rental: RentalEntity, lines: List<RentalLineEntity>) {
        upsertRental(rental)
        deleteLinesForRental(rental.id)
        upsertLines(lines)
    }
}
