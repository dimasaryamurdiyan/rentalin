package com.rentalin.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnPrimary
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInTheme
import com.rentalin.core.ui.RentalInAvatar
import com.rentalin.core.ui.RentalInSectionHeader
import com.rentalin.core.ui.RentalInStatusStyle
import com.rentalin.core.ui.RentalInSummaryCard

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = RentalInDimens.ScreenPadding),
        verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
    ) {
        item {
            Column(modifier = Modifier.padding(top = RentalInDimens.Xs)) {
                Text(
                    text = stringResource(R.string.dashboard_greeting),
                    style = MaterialTheme.typography.titleLarge,
                    color = RentalInOnSurface,
                )
                Text(
                    text = stringResource(R.string.dashboard_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = RentalInOnSurfaceVariant,
                )
            }
        }
        item {
            DashboardSummaryGrid()
        }
        item {
            RentalInSectionHeader(
                title = stringResource(R.string.dashboard_attention_title),
                action = {
                    Text(
                        text = stringResource(R.string.dashboard_action_view_all),
                        style = MaterialTheme.typography.labelMedium,
                        color = RentalInPrimary,
                    )
                },
            )
        }
        items(attentionItems()) { item ->
            DashboardAttentionRow(item)
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun DashboardScreenPreview() {
    RentalInTheme {
        DashboardScreen()
    }
}

@Composable
fun DashboardAddRentalButton() {
    FloatingActionButton(
        onClick = {},
        containerColor = RentalInPrimary,
        contentColor = RentalInOnPrimary,
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = stringResource(R.string.dashboard_action_add_rental),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardAddRentalButtonPreview() {
    RentalInTheme {
        DashboardAddRentalButton()
    }
}

@Composable
private fun DashboardSummaryGrid() {
    val summaries = summaryItems()
    Column(verticalArrangement = Arrangement.spacedBy(RentalInDimens.Sm)) {
        summaries.chunked(2).forEach { rowItems ->
            Row(horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Sm)) {
                rowItems.forEach { item ->
                    RentalInSummaryCard(
                        title = stringResource(item.title),
                        value = stringResource(item.value),
                        supportingText = stringResource(item.supportingText),
                        icon = item.icon,
                        style = item.style,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun DashboardSummaryGridPreview() {
    RentalInTheme {
        DashboardSummaryGrid()
    }
}

@Composable
private fun DashboardAttentionRow(item: AttentionItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
    ) {
        RentalInAvatar(initials = stringResource(item.initials))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(item.customer),
                style = MaterialTheme.typography.titleSmall,
                color = RentalInOnSurface,
            )
            Text(
                text = stringResource(item.item),
                style = MaterialTheme.typography.bodySmall,
                color = RentalInOnSurfaceVariant,
            )
        }
        Column {
            Text(
                text = stringResource(item.status),
                style = MaterialTheme.typography.labelSmall,
                color = item.styleColor(),
            )
            Text(
                text = stringResource(item.dueDate),
                style = MaterialTheme.typography.labelSmall,
                color = RentalInOnSurfaceVariant,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun DashboardAttentionRowPreview() {
    RentalInTheme {
        DashboardAttentionRow(attentionItems().first())
    }
}

@Composable
private fun AttentionItem.styleColor() = when (style) {
    RentalInStatusStyle.Overdue -> MaterialTheme.colorScheme.error
    RentalInStatusStyle.DueToday -> MaterialTheme.colorScheme.tertiary
    else -> RentalInPrimary
}

@Preview(showBackground = true)
@Composable
private fun AttentionItemStyleColorPreview() {
    RentalInTheme {
        Text(
            text = stringResource(R.string.dashboard_attention_overdue),
            color = attentionItems().first().styleColor(),
        )
    }
}

private data class SummaryItem(
    val title: Int,
    val value: Int,
    val supportingText: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val style: RentalInStatusStyle,
)

private data class AttentionItem(
    val initials: Int,
    val customer: Int,
    val item: Int,
    val status: Int,
    val dueDate: Int,
    val style: RentalInStatusStyle,
)

private fun summaryItems() = listOf(
    SummaryItem(
        R.string.dashboard_summary_active,
        R.string.dashboard_summary_active_count,
        R.string.dashboard_summary_rentals,
        Icons.Outlined.Inventory2,
        RentalInStatusStyle.Active,
    ),
    SummaryItem(
        R.string.dashboard_summary_due_today,
        R.string.dashboard_summary_due_today_count,
        R.string.dashboard_summary_rentals,
        Icons.Outlined.CalendarToday,
        RentalInStatusStyle.DueToday,
    ),
    SummaryItem(
        R.string.dashboard_summary_overdue,
        R.string.dashboard_summary_overdue_count,
        R.string.dashboard_summary_rentals,
        Icons.Outlined.Warning,
        RentalInStatusStyle.Overdue,
    ),
    SummaryItem(
        R.string.dashboard_summary_unpaid,
        R.string.dashboard_summary_unpaid_count,
        R.string.dashboard_summary_rentals,
        Icons.Outlined.CreditCard,
        RentalInStatusStyle.Reserved,
    ),
    SummaryItem(
        R.string.dashboard_summary_maintenance,
        R.string.dashboard_summary_maintenance_count,
        R.string.dashboard_summary_items,
        Icons.Outlined.Settings,
        RentalInStatusStyle.Maintenance,
    ),
)

private fun attentionItems() = listOf(
    AttentionItem(
        R.string.rentalin_initials_daniel,
        R.string.dashboard_customer_daniel_green,
        R.string.dashboard_item_dji_mavic,
        R.string.dashboard_attention_overdue,
        R.string.dashboard_due_apr_28,
        RentalInStatusStyle.Overdue,
    ),
    AttentionItem(
        R.string.rentalin_initials_sarah,
        R.string.dashboard_customer_sarah_johnson,
        R.string.dashboard_item_canon,
        R.string.dashboard_attention_due_today,
        R.string.dashboard_due_apr_30,
        RentalInStatusStyle.DueToday,
    ),
    AttentionItem(
        R.string.rentalin_initials_mike,
        R.string.dashboard_customer_mike_brown,
        R.string.dashboard_item_tent,
        R.string.dashboard_attention_payment_due,
        R.string.dashboard_due_apr_30,
        RentalInStatusStyle.Active,
    ),
)
