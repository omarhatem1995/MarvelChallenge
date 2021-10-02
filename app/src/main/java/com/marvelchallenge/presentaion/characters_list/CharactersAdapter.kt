package com.marvelchallenge.presentaion.characters_list

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

class CharactersAdapter(context: Context, activity: Activity, characters: List<CharacterDomainModel>?) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
    var mContext: Context? = context
    var activity: Activity? = activity
    var mData: List<CharacterDomainModel>? = characters

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.CharactersViewHolder {
        val v: View
        v = LayoutInflater.from(mContext).inflate(R.layout.character_list_item, parent, false)
        return CharactersViewHolder(v)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.CharactersViewHolder, position: Int) {
        val itemList = mData!![position]
        Log.d("languageList", " mData : " + itemList.imgUrl)

        mContext?.let { Glide.with(it).load(itemList.imgUrl).override(50, 50).into(holder.characterImage) }
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