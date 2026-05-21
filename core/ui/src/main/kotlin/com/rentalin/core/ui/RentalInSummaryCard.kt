package com.rentalin.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerHigh
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLowest
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInSummaryCard(
    title: String,
    value: String,
    supportingText: String,
    icon: ImageVector,
    style: RentalInStatusStyle,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.heightIn(min = 125.dp),
        shape = RoundedCornerShape(RentalInRadii.Large),
        colors = CardDefaults.cardColors(containerColor = RentalInSurfaceContainerLowest),
        border = BorderStroke(1.dp, RentalInSurfaceContainerHigh),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier = Modifier.padding(RentalInDimens.Md),
            verticalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
            ) {
                val colors = statusColors(style)
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(colors.container, RoundedCornerShape(RentalInRadii.Small)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = colors.content,
                        modifier = Modifier.size(16.dp),
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.content,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = RentalInOnSurface,
                )
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.labelSmall,
                    color = RentalInOnSurfaceVariant,
                )
            }
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
