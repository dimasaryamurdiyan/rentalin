package com.rentalin.core.model

data class Customer(
    val id: CustomerId,
    val name: String,
    val phone: String,
    val identityNumber: String?,
    val address: String?,
    val notes: String,
)

data class RentalCustomerSnapshot(
    val customerId: CustomerId?,
    val name: String,
    val phone: String,
    val identityNumber: String?,
    val address: String?,
)
