package com.rentalin.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInRadii

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
