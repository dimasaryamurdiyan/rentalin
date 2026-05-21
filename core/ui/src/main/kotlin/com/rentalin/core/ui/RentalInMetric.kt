package com.rentalin.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInMetric(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = RentalInOnSurface,
) {
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = RentalInOnSurfaceVariant)
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = valueColor,
            textAlign = TextAlign.Start,
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
