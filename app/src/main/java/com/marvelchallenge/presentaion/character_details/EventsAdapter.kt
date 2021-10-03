package com.marvelchallenge.presentaion.character_details

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvelchallenge.R
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.Events
import com.marvelchallenge.data.entities.characters.characterdetailsresponse.ItemsItem

class EventsAdapter(context: Context, activity: Activity, events: List<Events>) :
    RecyclerView.Adapter<EventsAdapter.ComicsViewHolder>() {
    var mContext: Context? = context
    var activity: Activity? = activity
    var mData: List<Events>? = events

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.ComicsViewHolder {
        val v: View
        v = LayoutInflater.from(mContext).inflate(R.layout.comic_list_item, parent, false)
        return ComicsViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventsAdapter.ComicsViewHolder, position: Int) {
        val itemList = mData!![position]
        Log.d("languageList", " mData : " + itemList.items?.get(0)?.name)

        mContext?.let { Glide.with(it).load(itemList.items?.get(0)?.resourceURI+".jpg").into(holder.ComicImage) }

        holder.ComicName.setText(itemList.items?.get(0)?.name)


    }

    override fun getItemCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ComicName: TextView
        var ComicImage: ImageView

        init {
            ComicImage = itemView.findViewById(R.id.imageComics)
            ComicName = itemView.findViewById(R.id.nameComics)
        }

    }


}