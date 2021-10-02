package com.marvelchallenge.presentaion.characters_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.CharacterResponse.ResultsItem
import com.marvelchallenge.data.gateways.remote.charachter_gateway.CharacterGatewayProvider
import com.marvelchallenge.data.gateways.remote.charachter_gateway.CharactersGateway
import com.marvelchallenge.data.mapper.toCharacterDomainModelList
import com.marvelchallenge.data.repo.marvel.CharactersRepoImpl
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterListUsecases
import com.marvelchallenge.framework.character.GetCharacterListUsecaseImpl
import com.marvelchallenge.presentaion.entities.character.CharacterViewState
import kotlinx.coroutines.launch

class CharactersListViewModel(

) : ViewModel() {
    val viewstate :MutableLiveData<CharacterViewState> = MutableLiveData()


     val  getCharacterListUsecase = createGetCharacterListUsecase()



    fun  getAllCharacters() = viewModelScope.launch{
        viewstate.postValue(CharacterViewState.Loading(true))
        val response = getCharacterListUsecase.invoke()
        when(response){
            is ApiResponse.Success    ->{
                val charList = response.body.data?.results.toCharacterDomainModelList()
                viewstate.postValue(CharacterViewState.Data(charList))
                viewstate.postValue(CharacterViewState.Loading(false))
            }
            is ApiResponse.NetworkError -> viewstate.postValue(CharacterViewState.NetworkFailure)
            else ->viewstate.postValue(CharacterViewState.Loading(false))
        }
    }

    private fun createGetCharacterListUsecase(): CharacterListUsecases.GetCharacterListUsecase {
        val charactersGateway : CharactersGateway = CharacterGatewayProvider.provideGateWay()
        return GetCharacterListUsecaseImpl(CharactersRepoImpl(
            charactersGateway
        ))
    }

}



