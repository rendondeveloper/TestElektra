package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rendonsoft.testelektra.databinding.ItemEvolutionChainPokemonBinding
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolutionChainView

class EvolutionChainAdapter(
    private val EvolutionChainList: List<PokemonEvolutionChainView>,
    private val listener: (PokemonEvolutionChainView) -> Unit
) :
    RecyclerView.Adapter<EvolutionChainAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEvolutionChainPokemonBinding.inflate(inflater, parent, false)
        return PokemonHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(EvolutionChainList[position], listener)
    }

    override fun getItemCount(): Int = EvolutionChainList.size

    class PokemonHolder(val binding: ItemEvolutionChainPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: PokemonEvolutionChainView,
            listener: (PokemonEvolutionChainView) -> Unit,
        ) {
            binding.item = item

            binding.root.setOnClickListener {
                listener(item)
            }
        }
    }
}