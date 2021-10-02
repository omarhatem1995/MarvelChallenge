package com.marvelchallenge.presentaion.characters_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.marvelchallenge.R
import androidx.activity.viewModels
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterListUsecases
import com.marvelchallenge.presentaion.entities.character.CharacterViewState

class CharachtersListActivity : AppCompatActivity()  , CharacterListUsecases.View{
    val  viewModel  by viewModels<CharactersListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charachters_list)
        viewModel.viewstate.observe(this , { viewstate->
            when(viewstate){
                is CharacterViewState.Loading ->{
                    Log.d("loading" , viewstate.show.toString())
                    renderLoading(viewstate.show)
                }
                is  CharacterViewState.NetworkFailure->{
                    Log.d("network" ,viewstate.toString())
                    renderNetworkFailure()
                }
                is  CharacterViewState.Data ->{
                    Log.d("data" , viewstate.data.toString())
                    viewstate.data?.let { renderCharacterList(it) }
                }

            }

        })
        viewModel.getAllCharacters()
    }

    override fun renderDetails(CharacterDomainModel: CharacterDomainModel) {
        // ex , start details activity
    }

    override fun renderLoading(show: Boolean) {
       // ex , loadingView.vis = Gone
    }

    override fun renderNetworkFailure() {
        // ex , networklayout.vis = Visable
    }

    override fun renderCharacterList(data: List<CharacterDomainModel>) {
        // ex  , submit data to adapter
    }
}