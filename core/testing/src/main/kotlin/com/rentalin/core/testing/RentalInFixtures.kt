package com.rentalin.core.testing

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

object RentalInFixtures {
    val tentItem = Item(
        id = ItemId("item-tent"),
        name = "2P Tent",
        category = "Shelter",
        rentalUnitType = RentalUnitType.Serialized,
        defaultPrice = MoneyAmount(150_000),
        notes = "",
        isArchived = false,
    )

    val trekkingPoleItem = Item(
        id = ItemId("item-poles"),
        name = "Trekking Poles",
        category = "Hiking",
        rentalUnitType = RentalUnitType.Quantity,
        defaultPrice = MoneyAmount(50_000),
        notes = "",
        isArchived = false,
    )

    val tentUnit = ItemUnit(
        id = ItemUnitId("unit-tent-a"),
        itemId = tentItem.id,
        code = "Tent A",
        condition = "Good",
        status = UnitStatus.Available,
        notes = "",
    )

    val trekkingPoleStock = QuantityStock(
        id = QuantityStockId("stock-poles"),
        itemId = trekkingPoleItem.id,
        totalQuantity = 6,
        lowStockThreshold = 2,
        notes = "",
    )

    val customer = Customer(
        id = CustomerId("customer-ari"),
        name = "Ari",
        phone = "081234567",
        identityNumber = null,
        address = null,
        notes = "",
    )

    fun rental(
        id: String = "rental-1",
        status: RentalStatus = RentalStatus.Reserved,
        startDate: LocalDate = LocalDate.of(2026, 5, 20),
        expectedReturnDate: LocalDate = LocalDate.of(2026, 5, 22),
        lines: List<RentalLine> = listOf(serializedLine(rentalId = RentalId(id))),
        rentalFee: MoneyAmount = MoneyAmount(150_000),
        depositAmount: MoneyAmount = MoneyAmount(100_000),
        amountPaid: MoneyAmount = MoneyAmount.Zero,
        extraCharges: MoneyAmount = MoneyAmount.Zero,
        refundAmount: MoneyAmount = MoneyAmount.Zero,
        isRefundCompleted: Boolean = false,
    ): Rental = Rental(
        id = RentalId(id),
        customer = RentalCustomerSnapshot(
            customerId = customer.id,
            name = customer.name,
            phone = customer.phone,
            identityNumber = customer.identityNumber,
            address = customer.address,
        ),
        startDate = startDate,
        expectedReturnDate = expectedReturnDate,
        actualReturnDate = null,
        status = status,
        lines = lines,
        rentalFee = rentalFee,
        depositAmount = depositAmount,
        amountPaid = amountPaid,
        extraCharges = extraCharges,
        refundAmount = refundAmount,
        isRefundCompleted = isRefundCompleted,
        conditionBeforeHandoff = "",
        conditionAfterReturn = "",
        pickupNotes = "",
        returnNotes = "",
        generalNotes = "",
    )

    fun serializedLine(
        rentalId: RentalId = RentalId("rental-1"),
        id: RentalLineId = RentalLineId("line-serialized"),
        unitId: ItemUnitId = tentUnit.id,
    ): RentalLine = RentalLine(
        id = id,
        rentalId = rentalId,
        itemId = tentItem.id,
        unitId = unitId,
        quantity = 1,
        price = tentItem.defaultPrice,
        conditionNotes = "",
    )

    fun quantityLine(
        rentalId: RentalId = RentalId("rental-1"),
        id: RentalLineId = RentalLineId("line-quantity"),
        quantity: Int = 2,
    ): RentalLine = RentalLine(
        id = id,
        rentalId = rentalId,
        itemId = trekkingPoleItem.id,
        unitId = null,
        quantity = quantity,
        price = trekkingPoleItem.defaultPrice,
        conditionNotes = "",
    )
}
