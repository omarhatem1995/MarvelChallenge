package com.marvelchallenge.data.gateways.remote.character_details_gateway

import com.marvelchallenge.data.core.NetworkResponseFactory.ApiResponseAdapterFactory
import com.marvelchallenge.data.core.ServiceCore
import com.marvelchallenge.data.gateways.remote.character_gateway.CharactersGateway
import com.marvelchallenge.domain.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterDetailsGatewayProvider {
    fun provideGateWay(): CharacterDetailsGateway {
        return Retrofit.Builder()
            .client(ServiceCore.provideOkHttpClient())
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterDetailsGateway::class.java)
    }
}