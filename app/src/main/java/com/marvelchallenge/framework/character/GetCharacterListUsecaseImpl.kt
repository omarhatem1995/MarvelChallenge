package com.marvelchallenge.framework.character

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterErrorResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.CharacterResponse
import com.marvelchallenge.domain.usecases.data.repo.CharactersRepo
import com.marvelchallenge.domain.usecases.ui.CharacterListUsecases

class GetCharacterListUsecaseImpl(
    val charactersRepo : CharactersRepo
) : CharacterListUsecases.GetCharacterListUsecase {
    override suspend fun invoke(): ApiResponse<CharacterResponse, CharacterErrorResponse> =
        charactersRepo.getAllCharacters()

}