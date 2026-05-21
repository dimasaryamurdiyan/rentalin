package com.rentalin.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOutlineVariant
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLowest
import com.rentalin.core.designsystem.theme.RentalInTheme

@Composable
fun RentalInSectionCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(RentalInRadii.Large),
        color = RentalInSurfaceContainerLowest,
        border = BorderStroke(1.dp, RentalInOutlineVariant),
        content = content,
    )
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun RentalInSectionCardPreview() {
    RentalInTheme {
        RentalInSectionCard(modifier = Modifier.padding(RentalInDimens.Md)) {
            Text(
                text = stringResource(R.string.preview_section_attention),
                modifier = Modifier.padding(RentalInDimens.Md),
            )
        }
    }
}
