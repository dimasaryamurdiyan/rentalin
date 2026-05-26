package com.rentalin.core.domain.repository

import com.rentalin.core.model.Customer
import com.rentalin.core.model.CustomerId
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun observeCustomers(): Flow<List<Customer>>
    suspend fun getCustomer(customerId: CustomerId): Customer?
    suspend fun saveCustomer(customer: Customer)
}
