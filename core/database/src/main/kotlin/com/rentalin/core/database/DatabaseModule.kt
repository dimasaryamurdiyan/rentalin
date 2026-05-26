package com.rentalin.core.database

import android.content.Context
import androidx.room.Room
import com.rentalin.core.database.dao.CustomerDao
import com.rentalin.core.database.dao.ItemDao
import com.rentalin.core.database.dao.ItemUnitDao
import com.rentalin.core.database.dao.QuantityStockDao
import com.rentalin.core.database.dao.RentalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRentalInDatabase(
        @ApplicationContext context: Context,
    ): RentalInDatabase = Room.databaseBuilder(
        context,
        RentalInDatabase::class.java,
        "rentalin.db",
    ).build()

    @Provides
    fun provideItemDao(database: RentalInDatabase): ItemDao = database.itemDao()

    @Provides
    fun provideItemUnitDao(database: RentalInDatabase): ItemUnitDao = database.itemUnitDao()

    @Provides
    fun provideQuantityStockDao(database: RentalInDatabase): QuantityStockDao =
        database.quantityStockDao()

    @Provides
    fun provideCustomerDao(database: RentalInDatabase): CustomerDao = database.customerDao()

    @Provides
    fun provideRentalDao(database: RentalInDatabase): RentalDao = database.rentalDao()
}
