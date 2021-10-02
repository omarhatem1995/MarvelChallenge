package com.marvelchallenge.presentaion.entities.character

import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel

sealed class CharacterViewState {
        data class  Loading(val show :Boolean) : CharacterViewState()
        object  NetworkFailure : CharacterViewState()
        data class  Data(val data : List<CharacterDomainModel>?) : CharacterViewState()
}
