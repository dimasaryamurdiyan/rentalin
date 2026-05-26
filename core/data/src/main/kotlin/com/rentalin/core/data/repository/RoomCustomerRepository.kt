package com.rentalin.core.data.repository

import com.rentalin.core.domain.repository.CustomerRepository
import com.rentalin.core.data.model.asEntity
import com.rentalin.core.data.model.asModel
import com.rentalin.core.database.dao.CustomerDao
import com.rentalin.core.model.Customer
import com.rentalin.core.model.CustomerId
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomCustomerRepository @Inject constructor(
    private val customerDao: CustomerDao,
) : CustomerRepository {
    override fun observeCustomers(): Flow<List<Customer>> =
        customerDao.observeCustomers().map { customers -> customers.map { it.asModel() } }

    override suspend fun getCustomer(customerId: CustomerId): Customer? =
        customerDao.getCustomer(customerId.value)?.asModel()

    override suspend fun saveCustomer(customer: Customer) {
        customerDao.upsertCustomer(customer.asEntity())
    }
}
