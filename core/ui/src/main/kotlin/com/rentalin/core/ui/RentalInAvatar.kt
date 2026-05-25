package com.rentalin.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainer
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInAvatar(
    initials: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(RentalInDimens.Avatar)
            .background(RentalInSurfaceContainer, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.titleSmall,
            color = RentalInOnSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RentalInAvatarPreview() {
    RentalInTheme {
        RentalInAvatar(
            initials = stringResource(R.string.preview_initials_daniel),
        )
    }
}
