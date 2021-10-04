package com.marvelchallenge.data.gateways.remote.character_search_gateway

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterErrorResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.CharacterResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchErrorResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterSearchGateway {

    @GET("characters/{name}")
    suspend fun searchCharacters(
        @Path("name") characterName: String,
        @Query("ts")  timeStamp : String,
        @Query("apikey")  apiKey : String,
        @Query("hash")  hash : String?,
    ) : ApiResponse<CharacterSearchResponse, CharacterSearchErrorResponse>


}