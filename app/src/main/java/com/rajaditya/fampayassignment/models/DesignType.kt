package com.rajaditya.fampay.models

import com.google.gson.annotations.SerializedName

enum class DesignType {

    @SerializedName("HC1")
    SMALL_DISPLAY_CARD,

    @SerializedName("HC3")
    BIG_DISPLAY_CARD,

    @SerializedName("HC5")
    IMAGE_CARD,

    @SerializedName("HC6")
    SMALL_CARD_WITH_ARROW,

    @SerializedName("HC9")
    DYNAMIC_WIDTH_CARD
}