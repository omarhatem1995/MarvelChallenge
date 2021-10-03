package com.marvelchallenge.data.gateways.remote.character_gateway

import com.marvelchallenge.data.core.NetworkResponseFactory.ApiResponseAdapterFactory
import com.marvelchallenge.data.core.ServiceCore
import com.marvelchallenge.domain.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterGatewayProvider {
    fun provideGateWay(): CharactersGateway {
        return Retrofit.Builder()
            .client(ServiceCore.provideOkHttpClient())
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersGateway::class.java)
    }
}