package com.rentalin.core.testing

import com.rentalin.core.common.repository.DashboardData
import com.rentalin.core.common.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDashboardRepository(
    initialData: DashboardData,
) : DashboardRepository {
    private val dataFlow = MutableStateFlow(initialData)

    override fun observeDashboardData(): Flow<DashboardData> = dataFlow

    fun setData(data: DashboardData) {
        dataFlow.value = data
    }
}
