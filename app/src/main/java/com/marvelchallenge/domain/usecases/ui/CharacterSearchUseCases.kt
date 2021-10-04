package com.marvelchallenge.domain.usecases.ui

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchErrorResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchResponse
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel

interface CharacterSearchUseCases {
    interface View {
        fun renderCharacter(CharacterDomainModel: CharacterDomainModel)
        fun renderLoading(show: Boolean)
        fun renderNetworkFailure()
        fun renderCharacterSearchingList(data: List<CharacterDomainModel>)
    }
    interface GetCharacterSearchingUseCase {
        suspend fun invoke(characterId : String): ApiResponse<CharacterSearchResponse, CharacterSearchErrorResponse>
    }
}