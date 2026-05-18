package com.rentalin.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = RentalInPrimary,
    onPrimary = Color.White,
    primaryContainer = RentalInPrimaryDark,
    onPrimaryContainer = Color.White,
    secondary = RentalInSuccess,
    onSecondary = Color.White,
    tertiary = RentalInWarning,
    onTertiary = RentalInText,
    background = RentalInBackground,
    onBackground = RentalInText,
    surface = RentalInSurface,
    onSurface = RentalInText,
    surfaceVariant = RentalInBackground,
    onSurfaceVariant = RentalInMutedText,
    outline = RentalInBorder,
    error = RentalInError,
    onError = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = RentalInPrimary,
    onPrimary = Color.White,
    secondary = RentalInSuccess,
    onSecondary = Color.White,
    tertiary = RentalInWarning,
    onTertiary = RentalInText,
    background = RentalInText,
    onBackground = RentalInBackground,
    surface = Color(0xFF1A1D1C),
    onSurface = RentalInBackground,
    surfaceVariant = Color(0xFF252927),
    onSurfaceVariant = Color(0xFFC8CBC7),
    outline = RentalInMaintenance,
    error = RentalInError,
    onError = Color.White,
)

@Composable
fun RentalInTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = RentalInTypography,
        content = content,
    )
}
