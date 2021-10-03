package com.marvelchallenge.presentaion.entities.character

import com.marvelchallenge.domain.entities.marvel.CharacterDetailsDomainModel
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel

sealed class CharacterDetailsViewState {
    data class  Loading(val show :Boolean) : CharacterDetailsViewState()
    object  NetworkFailure : CharacterDetailsViewState()
    data class  Data(val data : List<CharacterDetailsDomainModel>?) : CharacterDetailsViewState()
}