package com.marvelchallenge.presentaion.characters_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.marvelchallenge.R
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterListUsecases
import com.marvelchallenge.presentaion.characters_search.SearchingFragment
import com.marvelchallenge.presentaion.entities.character.CharacterViewState

class CharactersListActivity : AppCompatActivity()  , CharacterListUsecases.View{
    val  viewModel  by viewModels<CharactersListViewModel>()
    lateinit var recyclerView: RecyclerView
    lateinit var imageSearch : ImageView
    val searchingFragment = SearchingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charachters_list)

        viewModel.viewstate.observe(this, { viewstate ->
            when (viewstate) {
                is CharacterViewState.Loading -> {
                    Log.d("loadingIsCalled", viewstate.show.toString())
                    renderLoading(viewstate.show)

                }
                is CharacterViewState.NetworkFailure -> {
                    Log.d("network", viewstate.toString())
                    renderNetworkFailure()
                }
                is CharacterViewState.Data -> {
                    Log.d("data", viewstate.data.toString())
                    viewstate.data?.let { renderCharacterList(it) }
                }

            }

        })
        viewModel.getAllCharacters()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerviewCharacters)
        recyclerView.layoutManager = LinearLayoutManager(this)
        imageSearch = findViewById<ImageView>(R.id.searchIcon)
        imageSearch.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_search, searchingFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun renderDetails(CharacterDomainModel: CharacterDomainModel) {
        // ex , start details activity
    }

    override fun renderLoading(show: Boolean) {
       // ex , loadingView.vis = Gone
        Log.d("renderLoading", "is Called " + show)
    }

    override fun renderNetworkFailure() {
        // ex , networklayout.vis = Visable
    }

    override fun renderCharacterList(data: List<CharacterDomainModel>) {
        // ex  , submit data to adapter
        Log.d("characterList" , " is called" + data)
        val adapter = CharactersAdapter(this, this, data)
        recyclerView.adapter = adapter }
}