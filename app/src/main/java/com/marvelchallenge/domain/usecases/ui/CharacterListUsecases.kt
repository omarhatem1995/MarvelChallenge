package com.marvelchallenge.domain.usecases.ui

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterErrorResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.CharacterResponse
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel

interface CharacterListUsecases {
    interface  View{
        fun  renderDetails(CharacterDomainModel: CharacterDomainModel)
        fun  renderLoading(show: Boolean)
        fun renderNetworkFailure()
        fun renderCharacterList(data: List<CharacterDomainModel>)
    }

    interface GetCharacterListUsecase{
        suspend fun  invoke() : ApiResponse<CharacterResponse, CharacterErrorResponse>
    }

}