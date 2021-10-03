package com.marvelchallenge.presentaion.character_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ItemsItem
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ResultsItem
import com.marvelchallenge.data.gateways.remote.character_details_gateway.CharacterDetailsGateway
import com.marvelchallenge.data.gateways.remote.character_details_gateway.CharacterDetailsGatewayProvider
import com.marvelchallenge.data.repo.marvel.CharacterDetailsRepoImp
import com.marvelchallenge.domain.entities.marvel.CharacterDetailsDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterDetailsUseCases
import com.marvelchallenge.framework.character.GetCharacterDetailsUseCaseImpl
import com.marvelchallenge.presentaion.entities.character.CharacterDetailsViewState
import com.marvelchallenge.presentaion.entities.character.CharacterViewState
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(

) : ViewModel() {
    val viewState: MutableLiveData<CharacterDetailsViewState> = MutableLiveData()

    val getCharacterDetailsUseCase = createGetCharacterDetailsUseCase()


    fun getCharacterById(id: String) = viewModelScope.launch {
        viewState.postValue(CharacterDetailsViewState.Loading(true))
        val response = getCharacterDetailsUseCase.invoke(id)
        when (response) {
            is ApiResponse.Success -> {
                val charDetails = response.body.data?.results.toCharacterDetailsDomainModelList()
                viewState.postValue(CharacterDetailsViewState.Loading(false))
                viewState.postValue(CharacterDetailsViewState.Data(charDetails))
            }
            is ApiResponse.NetworkError -> viewState.postValue(CharacterDetailsViewState.NetworkFailure)
            else -> viewState.postValue(CharacterDetailsViewState.Loading(false))

        }
    }
    private fun createGetCharacterDetailsUseCase(): CharacterDetailsUseCases.GetCharacterDetailsUsecase {
        val characterDetailsGateway: CharacterDetailsGateway =
            CharacterDetailsGatewayProvider.provideGateWay()
        return GetCharacterDetailsUseCaseImpl(
            CharacterDetailsRepoImp(
                characterDetailsGateway
            )
        )
    }

}

private fun List<ResultsItem?>?.toCharacterDetailsDomainModelList():  List<CharacterDetailsDomainModel>? {
    return this?.map {
        CharacterDetailsDomainModel(it?.id!! , it.name , it.thumbnail?.path,it.thumbnail?.extension,
            it.description!! , it.comics!!.items as List<ItemsItem> , listOf(it.events)
        )
    }
}








