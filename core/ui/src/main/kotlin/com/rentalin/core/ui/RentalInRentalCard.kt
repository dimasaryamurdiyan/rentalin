package com.rentalin.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerHigh
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLowest
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInRentalCard(
    initials: String,
    customerName: String,
    itemName: String,
    dateRange: String,
    status: String,
    statusStyle: RentalInStatusStyle,
    amount: String,
    paymentLabel: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = RentalInSurfaceContainerLowest),
        border = BorderStroke(1.dp, RentalInSurfaceContainerHigh),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier.padding(RentalInDimens.Md),
            horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
        ) {
            RentalInAvatar(initials = initials)
            Row(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = customerName,
                        style = MaterialTheme.typography.titleSmall,
                        color = RentalInOnSurface,
                    )
                    Text(
                        text = itemName,
                        style = MaterialTheme.typography.bodySmall,
                        color = RentalInOnSurfaceVariant,
                    )
                    Text(
                        text = dateRange,
                        style = MaterialTheme.typography.labelSmall,
                        color = RentalInOnSurfaceVariant,
                        modifier = Modifier.padding(top = RentalInDimens.Unit),
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    RentalInStatusChip(label = status, style = statusStyle)
                    Text(
                        text = amount,
                        style = MaterialTheme.typography.titleSmall,
                        color = RentalInOnSurface,
                        modifier = Modifier.padding(top = RentalInDimens.Unit),
                    )
                    Text(
                        text = paymentLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = RentalInOnSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInRentalCardPreview() {
    RentalInTheme {
        RentalInRentalCard(
            initials = stringResource(R.string.preview_initials_daniel),
            customerName = stringResource(R.string.preview_customer_daniel),
            itemName = stringResource(R.string.preview_item_dji),
            dateRange = stringResource(R.string.preview_date_range),
            status = stringResource(R.string.preview_status_overdue),
            statusStyle = RentalInStatusStyle.Overdue,
            amount = stringResource(R.string.preview_amount_120),
            paymentLabel = stringResource(R.string.preview_payment_unpaid),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}
