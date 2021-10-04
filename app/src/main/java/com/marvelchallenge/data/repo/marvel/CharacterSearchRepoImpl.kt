package com.marvelchallenge.data.repo.marvel

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchErrorResponse
import com.marvelchallenge.data.entities.characters.charactersearchresponse.CharacterSearchResponse
import com.marvelchallenge.data.gateways.remote.character_details_gateway.CharacterDetailsGateway
import com.marvelchallenge.data.gateways.remote.character_search_gateway.CharacterSearchGateway
import com.marvelchallenge.domain.core.Constants
import com.marvelchallenge.domain.core.MD5Utils
import com.marvelchallenge.domain.usecases.data.repo.CharacterDetailsRepo
import com.marvelchallenge.domain.usecases.data.repo.CharacterSearchRepo

class CharacterSearchRepoImpl (
    val characterSearchGateway: CharacterSearchGateway
) : CharacterSearchRepo {
    override suspend fun getCharacterSearchByName(name: String): ApiResponse<CharacterSearchResponse, CharacterSearchErrorResponse> {
        val timeStamp = System.currentTimeMillis().toString()
        return characterSearchGateway.searchCharacters(
            name,
            timeStamp,
            Constants.PUPLIC_KEY ,
            MD5Utils.getMd5Key(timeStamp),
        )
    }
}
