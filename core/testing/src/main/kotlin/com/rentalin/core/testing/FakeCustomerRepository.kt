package com.rentalin.core.testing

import com.rentalin.core.domain.repository.CustomerRepository
import com.rentalin.core.model.Customer
import com.rentalin.core.model.CustomerId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCustomerRepository(
    customers: List<Customer> = emptyList(),
) : CustomerRepository {
    private val customersFlow = MutableStateFlow(customers)

    override fun observeCustomers(): Flow<List<Customer>> = customersFlow

    override suspend fun getCustomer(customerId: CustomerId): Customer? =
        customersFlow.value.firstOrNull { it.id == customerId }

    override suspend fun saveCustomer(customer: Customer) {
        customersFlow.value = customersFlow.value.upsert(customer) { it.id == customer.id }
    }
}
