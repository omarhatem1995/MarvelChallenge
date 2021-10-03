package com.marvelchallenge.domain.usecases.data.repo

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse

interface CharacterDetailsRepo {
    suspend fun getCharacterDetailsById(characterId : String) : ApiResponse<CharacterDetailsResponse, CharacterDetailsErrorResponse>
}