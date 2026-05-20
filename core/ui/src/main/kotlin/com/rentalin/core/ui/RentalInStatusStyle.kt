package com.rentalin.core.ui

import androidx.compose.ui.graphics.Color
import com.rentalin.core.designsystem.theme.RentalInBlueStatus
import com.rentalin.core.designsystem.theme.RentalInBlueStatusContainer
import com.rentalin.core.designsystem.theme.RentalInError
import com.rentalin.core.designsystem.theme.RentalInErrorContainer
import com.rentalin.core.designsystem.theme.RentalInLost
import com.rentalin.core.designsystem.theme.RentalInLostContainer
import com.rentalin.core.designsystem.theme.RentalInMaintenance
import com.rentalin.core.designsystem.theme.RentalInMaintenanceContainer
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInPrimaryFixed
import com.rentalin.core.designsystem.theme.RentalInPrimaryFixedDim
import com.rentalin.core.designsystem.theme.RentalInSuccess
import com.rentalin.core.designsystem.theme.RentalInSuccessContainer
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerHigh
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLow
import com.rentalin.core.designsystem.theme.RentalInTertiaryContainer
import com.rentalin.core.designsystem.theme.RentalInTertiaryFixed
import com.rentalin.core.designsystem.theme.RentalInWarning
import com.rentalin.core.designsystem.theme.RentalInWarningContainer

enum class RentalInStatusStyle {
    Active,
    Available,
    DueToday,
    Overdue,
    Paid,
    Rented,
    Reserved,
    Returned,
    Maintenance,
    Lost,
    LowStock,
    Neutral,
}

internal data class ChipColors(
    val container: Color,
    val content: Color,
)

internal fun statusColors(style: RentalInStatusStyle) = when (style) {
    RentalInStatusStyle.Active -> ChipColors(RentalInPrimaryFixed, RentalInPrimary)
    RentalInStatusStyle.Available -> ChipColors(RentalInSuccessContainer, RentalInSuccess)
    RentalInStatusStyle.DueToday -> ChipColors(RentalInTertiaryFixed, RentalInTertiaryContainer)
    RentalInStatusStyle.Overdue -> ChipColors(RentalInErrorContainer, RentalInError)
    RentalInStatusStyle.Paid -> ChipColors(RentalInSuccessContainer, RentalInSuccess)
    RentalInStatusStyle.Rented -> ChipColors(RentalInBlueStatusContainer, RentalInBlueStatus)
    RentalInStatusStyle.Reserved -> ChipColors(RentalInPrimaryFixedDim, RentalInPrimary)
    RentalInStatusStyle.Returned -> ChipColors(RentalInSurfaceContainerHigh, RentalInOnSurfaceVariant)
    RentalInStatusStyle.Maintenance -> ChipColors(RentalInMaintenanceContainer, RentalInMaintenance)
    RentalInStatusStyle.Lost -> ChipColors(RentalInLostContainer, RentalInLost)
    RentalInStatusStyle.LowStock -> ChipColors(RentalInWarningContainer, RentalInWarning)
    RentalInStatusStyle.Neutral -> ChipColors(RentalInSurfaceContainerLow, RentalInOnSurfaceVariant)
}
