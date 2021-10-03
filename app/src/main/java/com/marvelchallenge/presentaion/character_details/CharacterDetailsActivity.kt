package com.marvelchallenge.presentaion.character_details

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvelchallenge.R
import com.marvelchallenge.domain.entities.marvel.CharacterDetailsDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterDetailsUseCases
import com.marvelchallenge.presentaion.characters_list.CharactersListViewModel
import com.marvelchallenge.presentaion.entities.character.CharacterDetailsViewState
import com.marvelchallenge.presentaion.entities.character.CharacterViewState

class CharacterDetailsActivity : AppCompatActivity(), CharacterDetailsUseCases.View {

    val viewModel by viewModels<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        viewModel.viewState.observe(this, { viewState ->
            when (viewState) {
                is CharacterDetailsViewState.Loading -> {
                    Log.d("loadingIsCalled", viewState.show.toString())
                    renderLoading(viewState.show)

                }
                is CharacterDetailsViewState.NetworkFailure -> {
                    Log.d("network", viewState.toString())
                    renderNetworkFailure()
                }
                is CharacterDetailsViewState.Data -> {
                    Log.d("data", viewState.data.toString())
                    viewState.data?.let { renderCharacterDetails(it) }
                }

            }
        })
        viewModel.getCharacterById("1011334")

    }

    override fun renderDetails(characterDetailsDomainModel: CharacterDetailsDomainModel) {
    }

    override fun renderLoading(show: Boolean) {
    }

    override fun renderNetworkFailure() {
    }

    override fun renderCharacterDetails(data: List<CharacterDetailsDomainModel>) {
        val textView: TextView = findViewById(R.id.characterName)
        val textViewDescription: TextView = findViewById(R.id.characterDescription)
        val imageView: ImageView = findViewById(R.id.characterImageBackground)
        val recyclerViewComics: RecyclerView = findViewById(R.id.recyclerviewComics)
        val recyclerViewEvents: RecyclerView = findViewById(R.id.recyclerviewEvents)

        recyclerViewComics.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerViewEvents.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )


        val listComics = data.get(0).itemsItem
        val listOfEvents = data.get(0).events

        val adapterComics = ComicsAdapter(this,this,listComics)
        recyclerViewComics.adapter = adapterComics

        val adapterEvents = EventsAdapter(this,this,listOfEvents)
        recyclerViewEvents.adapter = adapterEvents

        Log.d("listComics", (listOfEvents).toString() + " ")

        textView.text = data.get(0).title
        if (!data.get(0).description.isEmpty()) {
            textViewDescription.text = data.get(0).description
        } else {
            textViewDescription.text = "No Description is Provided"

        }
        Glide.with(this).load(data.get(0).imgUrl + "." + data.get(0).imgExt)
            .into(imageView)

    }

}