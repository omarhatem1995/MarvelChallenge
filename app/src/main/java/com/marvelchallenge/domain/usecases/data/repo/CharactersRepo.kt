package com.marvelchallenge.domain.usecases.data.repo

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterErrorResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.CharacterResponse

interface CharactersRepo {
    suspend fun getAllCharacters() : ApiResponse<CharacterResponse, CharacterErrorResponse>
}