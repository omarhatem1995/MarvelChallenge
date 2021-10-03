package com.marvelchallenge.data.repo.marvel

import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterErrorResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.CharacterResponse
import com.marvelchallenge.data.gateways.remote.character_gateway.CharactersGateway
import com.marvelchallenge.domain.core.Constants
import com.marvelchallenge.domain.core.MD5Utils
import com.marvelchallenge.domain.usecases.data.repo.CharactersRepo

class CharactersRepoImpl(
    val charactersGateway : CharactersGateway
) : CharactersRepo {

    override suspend fun getAllCharacters(): ApiResponse<CharacterResponse, CharacterErrorResponse> {
        val timeStamp = System.currentTimeMillis().toString()
        return charactersGateway.getCharacters(
            timeStamp,
            Constants.PUPLIC_KEY ,
            MD5Utils.getMd5Key(timeStamp),
        )
    }
}