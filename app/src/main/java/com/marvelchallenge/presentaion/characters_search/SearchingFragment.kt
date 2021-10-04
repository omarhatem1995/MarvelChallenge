package com.marvelchallenge.presentaion.characters_search

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvelchallenge.R
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel
import com.marvelchallenge.domain.usecases.ui.CharacterListUsecases
import com.marvelchallenge.domain.usecases.ui.CharacterSearchUseCases
import com.marvelchallenge.presentaion.character_details.CharacterDetailsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchingFragment : Fragment() , CharacterSearchUseCases.View {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_searching, container, false)
        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview_searching)!!
        val searchingEditText = view?.findViewById<EditText>(R.id.searching_editText)

        val mViewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)

        searchingEditText?.addRxTextWatcher()
            ?.debounce(400, TimeUnit.MILLISECONDS)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
                if (!TextUtils.isEmpty(it)) {
                    //DO api request
                    it?.let { it1 -> mViewModel.getCharacterByName(it1) }
                }
            }

        recyclerView?.layoutManager = LinearLayoutManager(context)


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun EditText.addRxTextWatcher(): Observable<String?> {

        val flowable = Observable.create<String?> {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    it.onNext(s?.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
        }

        return flowable
    }

    override fun renderCharacter(CharacterDomainModel: CharacterDomainModel) {
    }

    override fun renderLoading(show: Boolean) {
    }

    override fun renderNetworkFailure() {
    }

    override fun renderCharacterSearchingList(data: List<CharacterDomainModel>) {
        val adapter = context?.let { CharactersSearchAdapter(it, data) }

        recyclerView?.adapter = adapter
    }

}