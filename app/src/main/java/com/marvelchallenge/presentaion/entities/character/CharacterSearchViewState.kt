package com.marvelchallenge.presentaion.entities.character

import com.marvelchallenge.data.entities.characters.charactersearchresponse.ResultsItem

sealed class CharacterSearchViewState {
    data class  Loading(val show :Boolean) : CharacterSearchViewState()
    object  NetworkFailure : CharacterSearchViewState()
    data class  Data(val data: List<ResultsItem>?) : CharacterSearchViewState()
}