package com.rentalin.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInTheme

@Preview(showBackground = true)
@Composable
private fun RentalInAvatarPreview() {
    RentalInTheme {
        RentalInAvatar(
            initials = stringResource(R.string.preview_initials_daniel),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RentalInStatusChipPreview() {
    RentalInTheme {
        Column(
            modifier = Modifier.padding(RentalInDimens.Md),
            verticalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
        ) {
            RentalInStatusChip(label = stringResource(R.string.preview_status_active), style = RentalInStatusStyle.Active)
            RentalInStatusChip(label = stringResource(R.string.preview_status_due_today), style = RentalInStatusStyle.DueToday)
            RentalInStatusChip(label = stringResource(R.string.preview_status_overdue), style = RentalInStatusStyle.Overdue)
            RentalInStatusChip(
                label = stringResource(R.string.preview_status_maintenance),
                style = RentalInStatusStyle.Maintenance,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInSummaryCardPreview() {
    RentalInTheme {
        RentalInSummaryCard(
            title = stringResource(R.string.preview_status_active),
            value = stringResource(R.string.preview_summary_value),
            supportingText = stringResource(R.string.preview_summary_supporting),
            icon = Icons.Outlined.Inventory2,
            style = RentalInStatusStyle.Active,
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInRentalCardPreview() {
    RentalInTheme {
        RentalInRentalCard(
            initials = stringResource(R.string.preview_initials_daniel),
            customerName = stringResource(R.string.preview_customer_daniel),
            itemName = stringResource(R.string.preview_item_dji),
            dateRange = stringResource(R.string.preview_date_range),
            status = stringResource(R.string.preview_status_overdue),
            statusStyle = RentalInStatusStyle.Overdue,
            amount = stringResource(R.string.preview_amount_120),
            paymentLabel = stringResource(R.string.preview_payment_unpaid),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInInventoryRowPreview() {
    RentalInTheme {
        RentalInInventoryRow(
            title = stringResource(R.string.preview_item_gopro),
            subtitle = stringResource(R.string.preview_sn_gopro),
            status = stringResource(R.string.preview_status_available),
            statusStyle = RentalInStatusStyle.Available,
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RentalInFilterChipPreview() {
    RentalInTheme {
        Column(
            modifier = Modifier.padding(RentalInDimens.Md),
            verticalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
        ) {
            RentalInFilterChip(label = stringResource(R.string.preview_filter_all), selected = true)
            RentalInFilterChip(label = stringResource(R.string.preview_filter_reserved), selected = false)
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInCustomerHeaderPreview() {
    RentalInTheme {
        RentalInCustomerHeader(
            initials = stringResource(R.string.preview_initials_daniel),
            name = stringResource(R.string.preview_customer_daniel),
            status = stringResource(R.string.preview_status_active),
            phone = stringResource(R.string.preview_phone_daniel),
            email = stringResource(R.string.preview_email_daniel),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInSectionHeaderPreview() {
    RentalInTheme {
        RentalInSectionHeader(
            title = stringResource(R.string.preview_section_attention),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInSectionCardPreview() {
    RentalInTheme {
        RentalInSectionCard(modifier = Modifier.padding(RentalInDimens.Md)) {
            RentalInMetric(
                label = stringResource(R.string.preview_total_rentals),
                value = stringResource(R.string.preview_total_rentals_value),
                modifier = Modifier.padding(RentalInDimens.Md),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInWarningCalloutPreview() {
    RentalInTheme {
        RentalInWarningCallout(
            title = stringResource(R.string.preview_warning_title),
            message = stringResource(R.string.preview_warning_message),
            icon = Icons.Outlined.Warning,
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInPrimaryBottomActionButtonPreview() {
    RentalInTheme {
        RentalInPrimaryBottomActionButton(
            label = stringResource(R.string.preview_action_create_rental),
            icon = Icons.Outlined.Add,
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RentalInMetricPreview() {
    RentalInTheme {
        RentalInMetric(
            label = stringResource(R.string.preview_balance),
            value = stringResource(R.string.preview_amount_120),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}
