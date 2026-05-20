package com.rentalin.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = RentalInPrimary,
    onPrimary = RentalInOnPrimary,
    primaryContainer = RentalInPrimaryContainer,
    onPrimaryContainer = RentalInOnPrimaryContainer,
    secondary = RentalInSecondary,
    onSecondary = RentalInOnSecondary,
    secondaryContainer = RentalInSecondaryContainer,
    onSecondaryContainer = RentalInOnSecondaryContainer,
    tertiary = RentalInTertiary,
    onTertiary = RentalInOnTertiary,
    tertiaryContainer = RentalInTertiaryContainer,
    onTertiaryContainer = RentalInOnTertiaryContainer,
    background = RentalInBackground,
    onBackground = RentalInOnBackground,
    surface = RentalInSurface,
    onSurface = RentalInOnSurface,
    surfaceVariant = RentalInSurfaceVariant,
    onSurfaceVariant = RentalInOnSurfaceVariant,
    outline = RentalInOutline,
    outlineVariant = RentalInOutlineVariant,
    error = RentalInError,
    onError = RentalInOnError,
    errorContainer = RentalInErrorContainer,
    onErrorContainer = RentalInOnErrorContainer,
    inverseSurface = RentalInInverseSurface,
    inverseOnSurface = RentalInInverseOnSurface,
    inversePrimary = RentalInInversePrimary,
    surfaceTint = RentalInSurfaceTint,
)

@Composable
fun RentalInTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = RentalInTypography,
        content = content,
    )
}
