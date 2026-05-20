package com.rentalin.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInTertiaryContainer
import com.rentalin.core.designsystem.theme.RentalInTertiaryFixed
import com.rentalin.core.designsystem.theme.RentalInWarning
import com.rentalin.core.designsystem.theme.RentalInWarningContainer

@Composable
fun RentalInWarningCallout(
    title: String,
    message: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(RentalInWarningContainer, RoundedCornerShape(RentalInRadii.Large))
            .border(1.dp, RentalInTertiaryFixed, RoundedCornerShape(RentalInRadii.Large))
            .padding(RentalInDimens.Sm),
        horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = RentalInWarning)
        Column {
            Text(text = title, style = MaterialTheme.typography.labelMedium, color = RentalInTertiaryContainer)
            Text(text = message, style = MaterialTheme.typography.bodySmall, color = RentalInTertiaryContainer)
        }
    }
}
