package com.marvelchallenge.framework.character

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchErrorResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchResponse
import com.marvelchallenge.domain.usecases.data.repo.CharacterSearchRepo
import com.marvelchallenge.domain.usecases.ui.CharacterSearchUseCases

class GetCharacterSearchUseCaseImpl (
    val characterSearchRepo: CharacterSearchRepo
) : CharacterSearchUseCases.GetCharacterSearchingUseCase{
    override suspend fun invoke(name : String): ApiResponse<CharacterSearchResponse, CharacterSearchErrorResponse> =
        characterSearchRepo.getCharacterSearchByName(name)

}