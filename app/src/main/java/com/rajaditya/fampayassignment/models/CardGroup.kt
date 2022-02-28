package com.rajaditya.fampay.models

import com.google.gson.annotations.SerializedName

data class CardGroup(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("design_type")
    val designType: DesignType,

    @SerializedName("cards")
    val cardList: List<Card>,

    @SerializedName("is_scrollable")
    val scrollable: Boolean,

    @SerializedName("height ")
    val height : Int?,
)