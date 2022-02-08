package com.rendonsoft.testelektra.home.module.fragment.abilities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rendonsoft.testelektra.databinding.ItemAbilityPokemonBinding
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilityPokemonView

class AbilitiesAdapter(
    private val abilityList: List<AbilityPokemonView>,
    private val listener: (AbilityPokemonView) -> Unit
) :
    RecyclerView.Adapter<AbilitiesAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAbilityPokemonBinding.inflate(inflater, parent, false)
        return PokemonHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(abilityList[position], listener)
    }

    override fun getItemCount(): Int = abilityList.size

    class PokemonHolder(val binding: ItemAbilityPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: AbilityPokemonView,
            listener: (AbilityPokemonView) -> Unit,
        ) {
            binding.item = item

            binding.root.setOnClickListener {
                listener(item)
            }
        }
    }
}