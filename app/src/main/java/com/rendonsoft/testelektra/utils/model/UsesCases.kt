package com.rendonsoft.testelektra.utils.model

import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.PokemonDetail
import com.rendonsoft.testelektra.home.module.fragment.home.repository.model.PokemonData
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal

interface LocalData {
    fun getAllPokemon() : List<PokemonLocal>
}

interface SaveData {
    fun addPokemon(pokemonList : List<PokemonLocal>)
}

interface HomeRemoteData{
    suspend  fun getAllPokemon(limit : Int) : ResponseData<PokemonData?>
}

interface DetailRemoteData{
    suspend  fun getDetailPokemon(name : String) : ResponseData<PokemonDetail?>
}

interface AbilitiesRemoteData{
    suspend  fun getAbilitiesPokemon(name : String) : ResponseData<AbilitiesPokemon?>
}

interface EvolutionChainRemoteData{
    suspend  fun getEvolutionChainPokemon(url : String) : ResponseData<PokemonEvolution?>
}