package com.rajaditya.fampay.network.responses

import com.google.gson.annotations.SerializedName
import com.rajaditya.fampay.models.CardGroup

data class ContextualCardsResponse(
    @SerializedName("card_groups")
    val cardGroup: List<CardGroup>
)