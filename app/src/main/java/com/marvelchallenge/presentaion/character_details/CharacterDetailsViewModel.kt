package com.marvelchallenge.presentaion.character_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvelchallenge.data.entities.base.ApiResponse
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ItemsItem
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ResultsItem
import com.marvelchallenge.data.gateways.remote.character_details_gateway.CharacterDetailsGateway
import com.marvelchallenge.data.gateways.remote.character_details_gateway.CharacterDetailsGatewayProvider
import com.marvelchallenge.data.gateways.remote.character_search_gateway.CharacterSearchGateway
import com.marvelchallenge.data.gateways.remote.character_search_gateway.CharacterSearchGatewayProvider
import com.marvelchallenge.data.repo.marvel.CharacterDetailsRepoImp
import com.marvelchallenge.data.repo.marvel.CharacterSearchRepoImpl
import com.marvelchallenge.domain.entities.marvel.CharacterDetailsDomainModel
import com.marvelchallenge.domain.entities.marvel.CharacterSearchDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterDetailsUseCases
import com.marvelchallenge.domain.usecases.ui.CharacterSearchUseCases
import com.marvelchallenge.framework.character.GetCharacterDetailsUseCaseImpl
import com.marvelchallenge.framework.character.GetCharacterSearchUseCaseImpl
import com.marvelchallenge.presentaion.entities.character.CharacterDetailsViewState
import com.marvelchallenge.presentaion.entities.character.CharacterSearchViewState
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(

) : ViewModel() {
    val viewState: MutableLiveData<CharacterDetailsViewState> = MutableLiveData()
    val viewStateSearch: MutableLiveData<CharacterSearchViewState> = MutableLiveData()

    val getCharacterDetailsUseCase = createGetCharacterDetailsUseCase()
    val getCharacterSearchUseCases = createGetCharacterSearchByNameUseCase()


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

    fun getCharacterByName(name: String) = viewModelScope.launch {
        viewStateSearch.postValue(CharacterSearchViewState.Loading(true))
        val response = getCharacterSearchUseCases.invoke(name)
        when (response) {
            is ApiResponse.Success -> {
                val charDetails = response.body.data?.results
                viewStateSearch.postValue(CharacterSearchViewState.Loading(false))
                viewStateSearch.postValue(CharacterSearchViewState.Data(charDetails))
            }
            is ApiResponse.NetworkError -> viewStateSearch.postValue(CharacterSearchViewState.NetworkFailure)
            else -> viewStateSearch.postValue(CharacterSearchViewState.Loading(false))

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

    private fun createGetCharacterSearchByNameUseCase(): CharacterSearchUseCases.GetCharacterSearchingUseCase {
        val characterSearchGateway: CharacterSearchGateway =
            CharacterSearchGatewayProvider.provideGateWay()
        return GetCharacterSearchUseCaseImpl(
            CharacterSearchRepoImpl(
                characterSearchGateway
            )
        )
    }

}

private fun List<ResultsItem?>?.toCharacterSearchDomainModelList(): List<CharacterSearchDomainModel>? {
    return this?.map {
        CharacterSearchDomainModel(
            it?.id!!, it.name, it.thumbnail?.path
        )
    }
}




private fun List<ResultsItem?>?.toCharacterDetailsDomainModelList(): List<CharacterDetailsDomainModel>? {
    return this?.map {
        CharacterDetailsDomainModel(
            it?.id!!, it.name, it.thumbnail?.path, it.thumbnail?.extension,
            it.description!!, it.comics!!.items as List<ItemsItem>, listOf(it.events)
        )
    }
}








