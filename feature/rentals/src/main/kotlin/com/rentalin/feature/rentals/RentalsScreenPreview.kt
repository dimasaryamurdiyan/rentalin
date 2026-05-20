package com.rentalin.feature.rentals

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rentalin.core.designsystem.theme.RentalInTheme

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun RentalsScreenPreview() {
    RentalInTheme {
        RentalsScreen()
    }
}
