package com.rendonsoft.testelektra.home.usescases.homeusescases.datalocal

import com.rendonsoft.testelektra.utils.model.SaveData
import com.rendonsoft.testelektra.utils.room.instance.TestElektraDataBase
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal


class HomeSaveLocalUsesCases(private val source : TestElektraDataBase) : SaveData {
    override fun addPokemon(pokemonList : List<PokemonLocal>) {
        source.pokemon().addPokemons(pokemonList)
    }
}