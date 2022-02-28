package com.rajaditya.fampayassignment.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Color.Yellow,
    secondary = Color(0xFFFFAD00)
)

@Composable
fun FampayAssignmentTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}