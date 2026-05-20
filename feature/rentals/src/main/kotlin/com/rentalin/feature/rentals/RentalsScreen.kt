package com.rentalin.feature.rentals

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.ui.RentalInFilterChip
import com.rentalin.core.ui.RentalInRentalCard
import com.rentalin.core.ui.RentalInStatusStyle

@Composable
fun RentalsScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(
                start = RentalInDimens.ScreenPaddingWide,
                end = RentalInDimens.ScreenPaddingWide,
                bottom = RentalInDimens.Md,
            ),
            verticalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
        ) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
            ) {
                RentalInFilterChip(label = stringResource(R.string.rentals_filter_active), selected = true)
                RentalInFilterChip(label = stringResource(R.string.rentals_filter_reserved), selected = false)
                RentalInFilterChip(label = stringResource(R.string.rentals_filter_overdue), selected = false)
                RentalInFilterChip(label = stringResource(R.string.rentals_filter_returned), selected = false)
            }
            RentalInFilterChip(label = stringResource(R.string.rentals_filter_unpaid), selected = false)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = RentalInDimens.Md),
            verticalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
        ) {
            items(rentalItems()) { item ->
                RentalInRentalCard(
                    initials = stringResource(item.initials),
                    customerName = stringResource(item.customer),
                    itemName = stringResource(item.item),
                    dateRange = stringResource(item.dateRange),
                    status = stringResource(item.status),
                    statusStyle = item.statusStyle,
                    amount = stringResource(item.amount),
                    paymentLabel = stringResource(item.payment),
                )
            }
        }
    }
}

private data class RentalItem(
    val initials: Int,
    val customer: Int,
    val item: Int,
    val dateRange: Int,
    val status: Int,
    val statusStyle: RentalInStatusStyle,
    val amount: Int,
    val payment: Int,
)

private fun rentalItems() = listOf(
    RentalItem(
        R.string.rentals_initials_daniel,
        R.string.rentals_customer_daniel,
        R.string.rentals_item_dji,
        R.string.rentals_date_apr_26,
        R.string.rentals_status_overdue,
        RentalInStatusStyle.Overdue,
        R.string.rentals_amount_120,
        R.string.rentals_payment_unpaid,
    ),
    RentalItem(
        R.string.rentals_initials_sarah,
        R.string.rentals_customer_sarah,
        R.string.rentals_item_canon,
        R.string.rentals_date_apr_30,
        R.string.rentals_status_due_today,
        RentalInStatusStyle.DueToday,
        R.string.rentals_amount_150,
        R.string.rentals_payment_paid,
    ),
    RentalItem(
        R.string.rentals_initials_mike,
        R.string.rentals_customer_mike,
        R.string.rentals_item_tent,
        R.string.rentals_date_apr_30_may_4,
        R.string.rentals_status_active,
        RentalInStatusStyle.Active,
        R.string.rentals_amount_60,
        R.string.rentals_payment_deposit,
    ),
    RentalItem(
        R.string.rentals_initials_lisa,
        R.string.rentals_customer_lisa,
        R.string.rentals_item_sound,
        R.string.rentals_date_may_1,
        R.string.rentals_status_active,
        RentalInStatusStyle.Active,
        R.string.rentals_amount_200,
        R.string.rentals_payment_paid,
    ),
    RentalItem(
        R.string.rentals_initials_james,
        R.string.rentals_customer_james,
        R.string.rentals_item_generator,
        R.string.rentals_date_may_2,
        R.string.rentals_status_reserved,
        RentalInStatusStyle.Reserved,
        R.string.rentals_amount_90,
        R.string.rentals_payment_deposit,
    ),
    RentalItem(
        R.string.rentals_initials_priya,
        R.string.rentals_customer_priya,
        R.string.rentals_item_projector,
        R.string.rentals_date_may_3,
        R.string.rentals_status_returned,
        RentalInStatusStyle.Returned,
        R.string.rentals_amount_110,
        R.string.rentals_payment_paid,
    ),
)
