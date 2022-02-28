package com.rajaditya.fampay.network.core

import com.rajaditya.fampay.network.responses.ContextualCardsResponse
import retrofit2.http.GET

interface ContextualCardsService {

    @GET("/v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad")
    suspend fun getContextualCards() : ContextualCardsResponse
}