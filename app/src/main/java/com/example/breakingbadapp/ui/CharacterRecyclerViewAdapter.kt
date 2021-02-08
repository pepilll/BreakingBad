package com.example.breakingbadapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R
import com.example.breakingbadapp.models.CharacterData
import kotlinx.android.synthetic.main.character_layout.view.*


internal class CharacterRecyclerViewAdapter
    (private val onCharacterClickListener: CharacterItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var charactersDataList: List<CharacterData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_layout, parent, false),
            onCharacterClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(charactersDataList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return charactersDataList.size
    }

    fun submitList(characterList: ArrayList<CharacterData>) {
        charactersDataList = characterList
    }

    fun setFilter(characterArrayList: ArrayList<CharacterData>) {
        charactersDataList = emptyList()
        charactersDataList =characterArrayList
        notifyDataSetChanged()
    }

    class CharacterViewHolder constructor(
        itemView: View,
        private val onCharacterClickListener: CharacterItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val image = itemView.characterImageView
        private val name = itemView.name

        fun bind(characterData: CharacterData) {
            name.text = characterData.name
            if (characterData.img.isNullOrEmpty()) {
                image.setImageResource(R.drawable.ic_launcher_background)
            } else {
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(characterData.img)
                    .into(image)
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onCharacterClickListener.onCharacterClick(adapterPosition)
        }
    }

    internal interface CharacterItemClickListener {
        fun onCharacterClick(position: Int)
    }
}