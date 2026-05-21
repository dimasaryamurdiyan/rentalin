package com.rentalin.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.rentalin.core.designsystem.theme.RentalInOnPrimary
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInCustomerHeader(
    initials: String,
    name: String,
    status: String,
    phone: String,
    email: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Md),
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(RentalInPrimary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = initials,
                style = MaterialTheme.typography.titleLarge,
                color = RentalInOnPrimary,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = RentalInOnSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                RentalInStatusChip(label = status, style = RentalInStatusStyle.Active)
            }
            Text(text = phone, style = MaterialTheme.typography.bodySmall, color = RentalInOnSurfaceVariant)
            Text(text = email, style = MaterialTheme.typography.bodySmall, color = RentalInOnSurfaceVariant)
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInCustomerHeaderPreview() {
    RentalInTheme {
        RentalInCustomerHeader(
            initials = stringResource(R.string.preview_initials_daniel),
            name = stringResource(R.string.preview_customer_daniel),
            status = stringResource(R.string.preview_status_active),
            phone = stringResource(R.string.preview_phone_daniel),
            email = stringResource(R.string.preview_email_daniel),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}
