package com.marvelchallenge.data.mapper

import com.marvelchallenge.data.entities.characters.CharacterResponse.ResultsItem
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel


fun  List<ResultsItem>?.toCharacterDomainModelList(): List<CharacterDomainModel>? {
    return this?.map {
        CharacterDomainModel(it.id , it.name , it.thumbnail?.path)
    }
}