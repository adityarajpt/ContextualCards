package com.rajaditya.fampayassignment.utils

import androidx.compose.ui.graphics.Color
import com.rajaditya.fampay.models.CardImage

fun CardImage?.toCardImageorEmpty(): CardImage {
    return this ?: CardImage("", "", "")
}

fun String?.toHextoJetpackColorOrBlack() : Color {
    return this?.toHexToJetpackColor() ?: Color.Black
}

fun String?.toHextoJetpackColorOrWhite() : Color {
    return this?.toHexToJetpackColor() ?: Color.White
}


