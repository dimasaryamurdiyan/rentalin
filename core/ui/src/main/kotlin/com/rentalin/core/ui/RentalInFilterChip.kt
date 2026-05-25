package com.rentalin.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.BorderStroke
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
import com.rentalin.core.designsystem.theme.RentalInOnPrimary
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInOutlineVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLowest
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInFilterChip(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(RentalInRadii.Pill),
        color = if (selected) RentalInPrimary else RentalInSurfaceContainerLowest,
        contentColor = if (selected) RentalInOnPrimary else RentalInOnSurfaceVariant,
        border = if (selected) null else BorderStroke(1.dp, RentalInOutlineVariant),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = RentalInDimens.Md, vertical = 6.dp),
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
