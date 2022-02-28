package com.rajaditya.fampay.network.core

object ApiRepository {

    private val networkModule = NetworkModule.getInstance()

    val contextualCardsApi : ContextualCardsService by lazy {
        networkModule.create(ContextualCardsService::class.java)
    }
}