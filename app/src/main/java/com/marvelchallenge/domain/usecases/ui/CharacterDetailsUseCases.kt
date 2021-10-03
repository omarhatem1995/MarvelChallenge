package com.marvelchallenge.domain.usecases.ui

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse
import com.marvelchallenge.domain.entities.marvel.CharacterDetailsDomainModel

interface CharacterDetailsUseCases {
    interface View {
        fun renderDetails(characterDetailsDomainModel: CharacterDetailsDomainModel)
        fun renderLoading(show: Boolean)
        fun renderNetworkFailure()
        fun renderCharacterDetails(data: List<CharacterDetailsDomainModel>)
    }

    interface GetCharacterDetailsUsecase {
        suspend fun invoke(characterId : String): ApiResponse<CharacterDetailsResponse, CharacterDetailsErrorResponse>
    }
}