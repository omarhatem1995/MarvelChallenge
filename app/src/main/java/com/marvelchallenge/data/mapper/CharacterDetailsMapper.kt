package com.marvelchallenge.data.mapper

import com.marvelchallenge.domain.entities.marvel.CharacterDetailsDomainModel
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ResultsItem

/*
fun List<ResultsItem>?.toCharacterDetailsDomainModelList(): List<CharacterDetailsDomainModel>? {
    return this?.map {
        CharacterDetailsDomainModel(
            it.id,
            it.name,
            it.thumbnail?.path,
            it.thumbnail?.extension,
            it.description!!,
            it.comics.items!!
        )
    }
}*/
