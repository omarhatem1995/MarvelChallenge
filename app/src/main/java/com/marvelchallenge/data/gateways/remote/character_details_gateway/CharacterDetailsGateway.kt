package com.marvelchallenge.data.gateways.remote.character_details_gateway

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterDetailsGateway {

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts")  timeStamp : String,
        @Query("apikey")  apiKey : String,
        @Query("hash")  hash : String?,
    ) : ApiResponse<CharacterDetailsResponse, CharacterDetailsErrorResponse>


}