package com.marvelchallenge.presentaion.characters_search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvelchallenge.R
import com.marvelchallenge.domain.entities.marvel.CharacterDomainModel

class CharactersSearchAdapter(context: Context, characters: List<CharacterDomainModel>?) :
    RecyclerView.Adapter<CharactersSearchAdapter.CharactersViewHolder>() {
    var mContext: Context? = context
    var mData: List<CharacterDomainModel>? = characters

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersSearchAdapter.CharactersViewHolder {
        val v: View
        v = LayoutInflater.from(mContext).inflate(R.layout.searching_item, parent, false)
        return CharactersViewHolder(v)
    }

    override fun onBindViewHolder(holder: CharactersSearchAdapter.CharactersViewHolder, position: Int) {
        val itemList = mData!![position]
        Log.d("languageList", " mData : " + itemList.imgUrl)

        mContext?.let { Glide.with(it).load(itemList.imgUrl+"."+itemList.imgExtension).into(holder.characterImage) }
        holder.characterName.setText(itemList.title)

        holder.characterImage.setOnClickListener{
//            val intent = Intent(mContext, CharacterDetailsActivity::class.java)
//            mContext?.startActivity(intent)
        }
//        holder.characterNameBackground.setblur
    }

    override fun getItemCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var characterName: TextView
        var characterImage: ImageView
//        var characterNameBackground : ImageView

        init {
            characterImage = itemView.findViewById(R.id.itemImage)
            characterName = itemView.findViewById(R.id.itemName)
//            characterNameBackground = itemView.findViewById(R.id.frameName)
        }

    }


}