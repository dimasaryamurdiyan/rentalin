package com.rentalin.core.data

import com.rentalin.core.common.repository.CustomerRepository
import com.rentalin.core.common.repository.DashboardRepository
import com.rentalin.core.common.repository.InventoryRepository
import com.rentalin.core.common.repository.RentalRepository
import com.rentalin.core.data.repository.RoomCustomerRepository
import com.rentalin.core.data.repository.RoomDashboardRepository
import com.rentalin.core.data.repository.RoomInventoryRepository
import com.rentalin.core.data.repository.RoomRentalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindInventoryRepository(repository: RoomInventoryRepository): InventoryRepository

    @Binds
    fun bindCustomerRepository(repository: RoomCustomerRepository): CustomerRepository

    @Binds
    fun bindRentalRepository(repository: RoomRentalRepository): RentalRepository

    @Binds
    fun bindDashboardRepository(repository: RoomDashboardRepository): DashboardRepository
}
