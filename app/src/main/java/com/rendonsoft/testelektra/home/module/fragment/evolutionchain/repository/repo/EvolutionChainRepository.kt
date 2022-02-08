package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.repo

import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.usescases.remote.EvolutionChainRemoteUsesCases
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class EvolutionChainRepository(
    private val remoteSource : EvolutionChainRemoteUsesCases
) {
    suspend fun getEvolutionChainPokemon(url : String) : ResponseData<PokemonEvolution?>{
        return remoteSource.getEvolutionChainPokemon(url)
    }
}