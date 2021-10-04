package com.marvelchallenge.domain.usecases.data.repo

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchErrorResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchResponse

interface CharacterSearchRepo {
    suspend fun getCharacterSearchByName(name : String) : ApiResponse<CharacterSearchResponse, CharacterSearchErrorResponse>

}