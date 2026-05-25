package com.rentalin.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInOutlineVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerHigh
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLowest
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInInventoryRow(
    title: String,
    subtitle: String,
    status: String,
    statusStyle: RentalInStatusStyle,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = RentalInDimens.Sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
    ) {
        Box(
            modifier = Modifier
                .size(RentalInDimens.SmallAvatar)
                .background(RentalInSurfaceContainerLowest, RoundedCornerShape(RentalInRadii.Small))
                .border(1.dp, RentalInSurfaceContainerHigh, RoundedCornerShape(RentalInRadii.Small)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = title.take(1),
                style = MaterialTheme.typography.labelLarge,
                color = RentalInPrimary,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = RentalInOnSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = RentalInOnSurfaceVariant,
            )
        }
        RentalInStatusChip(label = status, style = statusStyle)
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = RentalInOutlineVariant,
            modifier = Modifier.size(16.dp),
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
