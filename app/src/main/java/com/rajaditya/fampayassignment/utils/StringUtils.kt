package com.rajaditya.fampayassignment.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import com.rajaditya.fampay.models.FormattedText


fun String?.toStringOrEmpty(): String {
    return this ?: ""
}

fun String.toHexToJetpackColor(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (exception: Exception) {
        Color.White
    }
}