package com.marvelchallenge.data.gateways.remote.character_search_gateway

import com.marvelchallenge.data.core.NetworkResponseFactory.ApiResponseAdapterFactory
import com.marvelchallenge.data.core.ServiceCore
import com.marvelchallenge.domain.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterSearchGatewayProvider {
    fun provideGateWay(): CharacterSearchGateway {
        return Retrofit.Builder()
            .client(ServiceCore.provideOkHttpClient())
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterSearchGateway::class.java)
    }
}