package com.marvelchallenge.data.gateways.remote.character_gateway

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterErrorResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersGateway {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts")  timeStamp : String,
        @Query("apikey")  apiKey : String,
        @Query("hash")  hash : String?,
    ) : ApiResponse<CharacterResponse, CharacterErrorResponse>


}