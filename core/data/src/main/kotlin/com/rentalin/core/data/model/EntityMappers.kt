package com.rentalin.core.data.model

import com.rentalin.core.database.model.CustomerEntity
import com.rentalin.core.database.model.ItemEntity
import com.rentalin.core.database.model.ItemUnitEntity
import com.rentalin.core.database.model.QuantityStockEntity
import com.rentalin.core.database.model.RentalEntity
import com.rentalin.core.database.model.RentalLineEntity
import com.rentalin.core.database.model.RentalWithLines
import com.rentalin.core.model.Customer
import com.rentalin.core.model.CustomerId
import com.rentalin.core.model.Item
import com.rentalin.core.model.ItemId
import com.rentalin.core.model.ItemUnit
import com.rentalin.core.model.ItemUnitId
import com.rentalin.core.model.MoneyAmount
import com.rentalin.core.model.QuantityStock
import com.rentalin.core.model.QuantityStockId
import com.rentalin.core.model.Rental
import com.rentalin.core.model.RentalCustomerSnapshot
import com.rentalin.core.model.RentalId
import com.rentalin.core.model.RentalLine
import com.rentalin.core.model.RentalLineId
import com.rentalin.core.model.RentalStatus
import com.rentalin.core.model.RentalUnitType
import com.rentalin.core.model.UnitStatus
import java.time.LocalDate

internal fun ItemEntity.asModel(): Item = Item(
    id = ItemId(id),
    name = name,
    category = category,
    rentalUnitType = RentalUnitType.valueOf(rentalUnitType),
    defaultPrice = MoneyAmount(defaultPriceMinorUnits),
    notes = notes,
    isArchived = isArchived,
)

internal fun Item.asEntity(): ItemEntity = ItemEntity(
    id = id.value,
    name = name,
    category = category,
    rentalUnitType = rentalUnitType.name,
    defaultPriceMinorUnits = defaultPrice.minorUnits,
    notes = notes,
    isArchived = isArchived,
)

internal fun ItemUnitEntity.asModel(): ItemUnit = ItemUnit(
    id = ItemUnitId(id),
    itemId = ItemId(itemId),
    code = code,
    condition = condition,
    status = UnitStatus.valueOf(status),
    notes = notes,
)

internal fun ItemUnit.asEntity(): ItemUnitEntity = ItemUnitEntity(
    id = id.value,
    itemId = itemId.value,
    code = code,
    condition = condition,
    status = status.name,
    notes = notes,
)

internal fun QuantityStockEntity.asModel(): QuantityStock = QuantityStock(
    id = QuantityStockId(id),
    itemId = ItemId(itemId),
    totalQuantity = totalQuantity,
    lowStockThreshold = lowStockThreshold,
    notes = notes,
)

internal fun QuantityStock.asEntity(): QuantityStockEntity = QuantityStockEntity(
    id = id.value,
    itemId = itemId.value,
    totalQuantity = totalQuantity,
    lowStockThreshold = lowStockThreshold,
    notes = notes,
)

internal fun CustomerEntity.asModel(): Customer = Customer(
    id = CustomerId(id),
    name = name,
    phone = phone,
    identityNumber = identityNumber,
    address = address,
    notes = notes,
)

internal fun Customer.asEntity(): CustomerEntity = CustomerEntity(
    id = id.value,
    name = name,
    phone = phone,
    identityNumber = identityNumber,
    address = address,
    notes = notes,
)

internal fun RentalWithLines.asModel(): Rental = Rental(
    id = RentalId(rental.id),
    customer = RentalCustomerSnapshot(
        customerId = rental.customerId?.let(::CustomerId),
        name = rental.customerName,
        phone = rental.customerPhone,
        identityNumber = rental.customerIdentityNumber,
        address = rental.customerAddress,
    ),
    startDate = LocalDate.parse(rental.startDate),
    expectedReturnDate = LocalDate.parse(rental.expectedReturnDate),
    actualReturnDate = rental.actualReturnDate?.let(LocalDate::parse),
    status = RentalStatus.valueOf(rental.status),
    lines = lines.map { it.asModel() },
    rentalFee = MoneyAmount(rental.rentalFeeMinorUnits),
    depositAmount = MoneyAmount(rental.depositAmountMinorUnits),
    amountPaid = MoneyAmount(rental.amountPaidMinorUnits),
    extraCharges = MoneyAmount(rental.extraChargesMinorUnits),
    refundAmount = MoneyAmount(rental.refundAmountMinorUnits),
    isRefundCompleted = rental.isRefundCompleted,
    conditionBeforeHandoff = rental.conditionBeforeHandoff,
    conditionAfterReturn = rental.conditionAfterReturn,
    pickupNotes = rental.pickupNotes,
    returnNotes = rental.returnNotes,
    generalNotes = rental.generalNotes,
)

internal fun Rental.asEntity(): RentalEntity = RentalEntity(
    id = id.value,
    customerId = customer.customerId?.value,
    customerName = customer.name,
    customerPhone = customer.phone,
    customerIdentityNumber = customer.identityNumber,
    customerAddress = customer.address,
    startDate = startDate.toString(),
    expectedReturnDate = expectedReturnDate.toString(),
    actualReturnDate = actualReturnDate?.toString(),
    status = status.name,
    rentalFeeMinorUnits = rentalFee.minorUnits,
    depositAmountMinorUnits = depositAmount.minorUnits,
    amountPaidMinorUnits = amountPaid.minorUnits,
    extraChargesMinorUnits = extraCharges.minorUnits,
    refundAmountMinorUnits = refundAmount.minorUnits,
    isRefundCompleted = isRefundCompleted,
    conditionBeforeHandoff = conditionBeforeHandoff,
    conditionAfterReturn = conditionAfterReturn,
    pickupNotes = pickupNotes,
    returnNotes = returnNotes,
    generalNotes = generalNotes,
)

internal fun RentalLineEntity.asModel(): RentalLine = RentalLine(
    id = RentalLineId(id),
    rentalId = RentalId(rentalId),
    itemId = ItemId(itemId),
    unitId = unitId?.let(::ItemUnitId),
    quantity = quantity,
    price = MoneyAmount(priceMinorUnits),
    conditionNotes = conditionNotes,
)

internal fun RentalLine.asEntity(): RentalLineEntity = RentalLineEntity(
    id = id.value,
    rentalId = rentalId.value,
    itemId = itemId.value,
    unitId = unitId?.value,
    quantity = quantity,
    priceMinorUnits = price.minorUnits,
    conditionNotes = conditionNotes,
)
