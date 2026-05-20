package com.rentalin.feature.inventory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInPrimaryFixed
import com.rentalin.core.ui.RentalInFilterChip
import com.rentalin.core.ui.RentalInInventoryRow
import com.rentalin.core.ui.RentalInSectionHeader
import com.rentalin.core.ui.RentalInStatusChip
import com.rentalin.core.ui.RentalInStatusStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InventoryScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = RentalInDimens.ScreenPaddingWide),
        verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
    ) {
        item {
            FlowRow(
                modifier = Modifier.padding(top = RentalInDimens.Md),
                horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
                verticalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
            ) {
                filterLabels().forEachIndexed { index, label ->
                    RentalInFilterChip(label = stringResource(label), selected = index == 0)
                }
            }
        }
        item {
            InventorySectionHeader(
                title = stringResource(R.string.inventory_serialized_title),
                count = stringResource(R.string.inventory_serialized_count),
            )
        }
        items(serializedItems()) { item ->
            RentalInInventoryRow(
                title = stringResource(item.title),
                subtitle = stringResource(item.subtitle),
                status = stringResource(item.status),
                statusStyle = item.statusStyle,
            )
        }
        item {
            InventorySectionHeader(
                title = stringResource(R.string.inventory_stock_title),
                count = stringResource(R.string.inventory_stock_count),
                modifier = Modifier.padding(top = RentalInDimens.Sm),
            )
        }
        items(stockItems()) { item ->
            RentalInInventoryRow(
                title = stringResource(item.title),
                subtitle = stringResource(item.subtitle),
                status = stringResource(item.status),
                statusStyle = item.statusStyle,
            )
        }
    }
}

@Composable
private fun InventorySectionHeader(
    title: String,
    count: String,
    modifier: Modifier = Modifier,
) {
    RentalInSectionHeader(
        title = title,
        modifier = modifier,
        action = {
            RentalInStatusChip(label = count, style = RentalInStatusStyle.Active)
        },
    )
}

private data class InventoryItem(
    val title: Int,
    val subtitle: Int,
    val status: Int,
    val statusStyle: RentalInStatusStyle,
)

private fun filterLabels() = listOf(
    R.string.inventory_filter_all,
    R.string.inventory_filter_camera,
    R.string.inventory_filter_audio,
    R.string.inventory_filter_tools,
    R.string.inventory_filter_outdoor,
    R.string.inventory_filter_power,
    R.string.inventory_filter_accessories,
)

private fun serializedItems() = listOf(
    InventoryItem(
        R.string.inventory_item_dji,
        R.string.inventory_sn_dji,
        R.string.inventory_status_available,
        RentalInStatusStyle.Available,
    ),
    InventoryItem(
        R.string.inventory_item_canon,
        R.string.inventory_sn_canon,
        R.string.inventory_status_rented,
        RentalInStatusStyle.Rented,
    ),
    InventoryItem(
        R.string.inventory_item_gopro,
        R.string.inventory_sn_gopro,
        R.string.inventory_status_available,
        RentalInStatusStyle.Available,
    ),
    InventoryItem(
        R.string.inventory_item_sony,
        R.string.inventory_sn_sony,
        R.string.inventory_status_maintenance,
        RentalInStatusStyle.Maintenance,
    ),
    InventoryItem(
        R.string.inventory_item_macbook,
        R.string.inventory_sn_macbook,
        R.string.inventory_status_lost,
        RentalInStatusStyle.Lost,
    ),
    InventoryItem(
        R.string.inventory_item_zoom,
        R.string.inventory_sn_zoom,
        R.string.inventory_status_available,
        RentalInStatusStyle.Available,
    ),
)

private fun stockItems() = listOf(
    InventoryItem(
        R.string.inventory_item_tent,
        R.string.inventory_units_12,
        R.string.inventory_status_available,
        RentalInStatusStyle.Available,
    ),
    InventoryItem(
        R.string.inventory_item_tripod,
        R.string.inventory_units_3,
        R.string.inventory_status_low_stock,
        RentalInStatusStyle.LowStock,
    ),
    InventoryItem(
        R.string.inventory_item_cable,
        R.string.inventory_units_8,
        R.string.inventory_status_available,
        RentalInStatusStyle.Available,
    ),
)
