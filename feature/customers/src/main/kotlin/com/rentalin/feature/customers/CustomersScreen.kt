package com.rentalin.feature.customers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInError
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInSuccess
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainer
import com.rentalin.core.ui.RentalInCustomerHeader
import com.rentalin.core.ui.RentalInMetric
import com.rentalin.core.ui.RentalInPrimaryBottomActionButton
import com.rentalin.core.ui.RentalInSectionCard
import com.rentalin.core.ui.RentalInSectionHeader
import com.rentalin.core.ui.RentalInStatusChip
import com.rentalin.core.ui.RentalInStatusStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomersScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = RentalInDimens.ScreenPaddingWide),
        verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
    ) {
        item {
            Column(
                modifier = Modifier.padding(top = RentalInDimens.Md),
                verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
            ) {
                RentalInCustomerHeader(
                    initials = stringResource(R.string.customers_initials_daniel),
                    name = stringResource(R.string.customers_name_daniel),
                    status = stringResource(R.string.customers_status_active),
                    phone = stringResource(R.string.customers_phone_daniel),
                    email = stringResource(R.string.customers_email_daniel),
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
                    verticalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
                ) {
                    CustomerAction(
                        label = stringResource(R.string.customers_action_call),
                        icon = Icons.Outlined.Call,
                    )
                    CustomerAction(
                        label = stringResource(R.string.customers_action_message),
                        icon = Icons.AutoMirrored.Outlined.Message,
                    )
                    CustomerAction(
                        label = stringResource(R.string.customers_action_email),
                        icon = Icons.Outlined.Email,
                    )
                    CustomerAction(
                        label = stringResource(R.string.customers_action_edit),
                        icon = Icons.Outlined.Edit,
                    )
                }
            }
        }
        item {
            AccountOverview()
        }
        item {
            CustomerNotes()
        }
        item {
            ActivityTabs()
        }
        items(customerRentals()) { rental ->
            CustomerRentalRow(rental)
        }
        item {
            RentalInPrimaryBottomActionButton(
                label = stringResource(R.string.customers_action_new_rental),
                icon = Icons.Outlined.Add,
                modifier = Modifier.padding(bottom = RentalInDimens.Lg),
            )
        }
    }
}

@Composable
private fun CustomerAction(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    RentalInSectionCard(modifier = Modifier.fillMaxWidth(0.46f)) {
        Column(
            modifier = Modifier.padding(RentalInDimens.Sm),
            verticalArrangement = Arrangement.spacedBy(RentalInDimens.Unit),
        ) {
            androidx.compose.material3.Icon(
                imageVector = icon,
                contentDescription = null,
                tint = RentalInPrimary,
            )
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = RentalInOnSurfaceVariant)
        }
    }
}

@Composable
private fun AccountOverview() {
    Column(verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md)) {
        Text(
            text = stringResource(R.string.customers_account_overview),
            style = MaterialTheme.typography.titleSmall,
            color = RentalInOnSurface,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Lg)) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md)) {
                RentalInMetric(
                    label = stringResource(R.string.customers_total_rentals),
                    value = stringResource(R.string.customers_total_rentals_value),
                )
                RentalInMetric(
                    label = stringResource(R.string.customers_unpaid_balance),
                    value = stringResource(R.string.customers_unpaid_balance_value),
                    valueColor = RentalInError,
                )
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md)) {
                RentalInMetric(
                    label = stringResource(R.string.customers_total_spent),
                    value = stringResource(R.string.customers_total_spent_value),
                )
                RentalInMetric(
                    label = stringResource(R.string.customers_security_deposit),
                    value = stringResource(R.string.customers_security_deposit_value),
                    valueColor = RentalInSuccess,
                )
            }
        }
    }
}

@Composable
private fun CustomerNotes() {
    Column(verticalArrangement = Arrangement.spacedBy(RentalInDimens.Xs)) {
        RentalInSectionHeader(
            title = stringResource(R.string.customers_notes_title),
            action = {
                Text(
                    text = stringResource(R.string.customers_action_edit),
                    style = MaterialTheme.typography.labelSmall,
                    color = RentalInPrimary,
                )
            },
        )
        Text(
            text = stringResource(R.string.customers_notes_body),
            style = MaterialTheme.typography.bodySmall,
            color = RentalInOnSurfaceVariant,
        )
    }
}

@Composable
private fun ActivityTabs() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.customers_tab_rentals),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleSmall,
            color = RentalInPrimary,
        )
        Text(
            text = stringResource(R.string.customers_tab_payments),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleSmall,
            color = RentalInOnSurfaceVariant,
        )
        Text(
            text = stringResource(R.string.customers_tab_activity),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleSmall,
            color = RentalInOnSurfaceVariant,
        )
    }
}

@Composable
private fun CustomerRentalRow(rental: CustomerRental) {
    Column(verticalArrangement = Arrangement.spacedBy(RentalInDimens.Unit)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(rental.item),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall,
                color = RentalInOnSurface,
            )
            RentalInStatusChip(
                label = stringResource(rental.status),
                style = rental.statusStyle,
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(rental.dateRange),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodySmall,
                color = RentalInOnSurfaceVariant,
            )
            Text(
                text = stringResource(rental.amount),
                style = MaterialTheme.typography.titleSmall,
                color = RentalInOnSurface,
            )
        }
        HorizontalDivider(color = RentalInSurfaceContainer)
    }
}

private data class CustomerRental(
    val item: Int,
    val status: Int,
    val statusStyle: RentalInStatusStyle,
    val dateRange: Int,
    val amount: Int,
)

private fun customerRentals() = listOf(
    CustomerRental(
        R.string.customers_item_drone_gopro,
        R.string.customers_status_due_today,
        RentalInStatusStyle.DueToday,
        R.string.customers_date_first,
        R.string.customers_amount_220,
    ),
    CustomerRental(
        R.string.customers_item_drone,
        R.string.customers_status_overdue,
        RentalInStatusStyle.Overdue,
        R.string.customers_date_second,
        R.string.customers_amount_120,
    ),
    CustomerRental(
        R.string.customers_item_canon,
        R.string.customers_status_paid,
        RentalInStatusStyle.Paid,
        R.string.customers_date_third,
        R.string.customers_amount_150,
    ),
)
