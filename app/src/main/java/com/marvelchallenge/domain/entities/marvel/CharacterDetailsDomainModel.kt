package com.marvelchallenge.domain.entities.marvel

import com.marvelchallenge.data.entities.characters.characterdetailsresponse.Events
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ItemsItem


data class CharacterDetailsDomainModel(
    val id:Int,
    val title:String,
    val imgUrl: String?,
    val imgExt: String?,
    val description: String,
    val itemsItem: List<ItemsItem>,
    val events : List<Events>
)

data class ItemsItem(
    val name : String,
    val resourceURI: String
)