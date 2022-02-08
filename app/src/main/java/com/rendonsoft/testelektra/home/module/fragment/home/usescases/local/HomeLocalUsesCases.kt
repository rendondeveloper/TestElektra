package com.rendonsoft.testelektra.home.usescases.homeusescases.datalocal

import com.rendonsoft.testelektra.utils.model.LocalData
import com.rendonsoft.testelektra.utils.room.instance.TestElektraDataBase
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal


class HomeLocalUsesCases(private val source : TestElektraDataBase) : LocalData {
    override fun getAllPokemon() : List<PokemonLocal>{
        return source.pokemon().getAll()
    }
}