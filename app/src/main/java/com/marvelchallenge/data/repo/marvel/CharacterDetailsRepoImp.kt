package com.marvelchallenge.data.repo.marvel

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsErrorResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.CharacterDetailsResponse
import com.marvelchallenge.data.gateways.remote.character_details_gateway.CharacterDetailsGateway
import com.marvelchallenge.domain.core.Constants
import com.marvelchallenge.domain.core.MD5Utils
import com.marvelchallenge.domain.usecases.data.repo.CharacterDetailsRepo

class CharacterDetailsRepoImp(
    val characterDetailsGateway: CharacterDetailsGateway
) : CharacterDetailsRepo{
    override suspend fun getCharacterDetailsById(characterId: String): ApiResponse<CharacterDetailsResponse, CharacterDetailsErrorResponse> {
        val timeStamp = System.currentTimeMillis().toString()
        return characterDetailsGateway.getCharacterById(
            characterId,
            timeStamp,
            Constants.PUPLIC_KEY ,
            MD5Utils.getMd5Key(timeStamp),
        )
    }

}
