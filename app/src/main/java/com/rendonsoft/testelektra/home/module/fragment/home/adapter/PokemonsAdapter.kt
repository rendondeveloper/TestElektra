package com.rendonsoft.testelektra.home.module.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rendonsoft.testelektra.databinding.ItemPokemonBinding
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal

class PokemonsAdapter(
    private val pokemonList: List<PokemonLocal>,
    private val listener: (PokemonLocal) -> Unit
) :
    RecyclerView.Adapter<PokemonsAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)
        return PokemonHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemonList[position], listener)
    }

    override fun getItemCount(): Int = pokemonList.size

    class PokemonHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: PokemonLocal,
            listener: (PokemonLocal) -> Unit,
        ) {
            binding.item = item

            binding.root.setOnClickListener {
                listener(item)
            }
        }
    }
}