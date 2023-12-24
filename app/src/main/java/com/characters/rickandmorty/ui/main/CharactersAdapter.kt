package com.characters.rickandmorty.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.characters.rickandmorty.databinding.CharRowBinding
import com.characters.rickandmorty.util.downloadImage

class CharactersAdapter(val charList : ArrayList<com.characters.rickandmorty.model.Result>) : RecyclerView.Adapter<CharactersAdapter.CharHolder>() {


    private val TAG = "CharactersAdapter"

    class CharHolder (val binding : CharRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharHolder {
        val binding = CharRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharHolder(binding)
    }

    override fun onBindViewHolder(holder: CharHolder, position: Int) {

        val char = charList.get(position)

        holder.binding.apply {

            charImageView.downloadImage(char.image, CircularProgressDrawable(holder.itemView.context))
            charNameTextView.text = char.name

        }

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(char)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun refreshList(newList : List<com.characters.rickandmorty.model.Result>){
        charList.clear()
        charList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return charList.size
    }
}