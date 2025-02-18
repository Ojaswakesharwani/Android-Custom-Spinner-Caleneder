package com.example.myspinnercalender.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object PickerTheme {

    val colors: PickerColors
        @Composable @ReadOnlyComposable get() = if (!isSystemInDarkTheme()) lightColors() else darkColors()

    val typography: PickerTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions: PickerDimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current

    val pallets: List<androidx.compose.ui.graphics.Color>
        @Composable @ReadOnlyComposable get() = if (!isSystemInDarkTheme()) lightPallet else darkPallet
}