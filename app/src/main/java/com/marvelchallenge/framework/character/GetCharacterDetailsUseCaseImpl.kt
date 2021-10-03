package com.marvelchallenge.framework.character

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse
import com.marvelchallenge.domain.usecases.data.repo.CharacterDetailsRepo
import com.marvelchallenge.domain.usecases.ui.CharacterDetailsUseCases

class GetCharacterDetailsUseCaseImpl(
    val characterDetailsRepo: CharacterDetailsRepo
) : CharacterDetailsUseCases.GetCharacterDetailsUsecase{
    override suspend fun invoke(characterId : String): ApiResponse<CharacterDetailsResponse, CharacterDetailsErrorResponse> =
        characterDetailsRepo.getCharacterDetailsById(characterId)

}