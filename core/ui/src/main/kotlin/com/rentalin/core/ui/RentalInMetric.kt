package com.rentalin.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant

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
