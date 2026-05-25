package com.rentalin.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInStatusChip(
    label: String,
    style: RentalInStatusStyle,
    modifier: Modifier = Modifier,
) {
    val colors = statusColors(style)
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(RentalInRadii.Pill),
        color = colors.container,
        contentColor = colors.content,
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = RentalInDimens.Xs, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall,
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
