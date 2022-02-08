package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.usescases.remote

import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.retrofit.EvolutionChainApi
import com.rendonsoft.testelektra.utils.model.EvolutionChainRemoteData
import com.rendonsoft.testelektra.utils.retrofit.builder.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.managercall.ManagerCall
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class EvolutionChainRemoteUsesCases(private val source : RetrofitApp<EvolutionChainApi>) : ManagerCall(),
    EvolutionChainRemoteData {

    override suspend fun getEvolutionChainPokemon(url: String): ResponseData<PokemonEvolution?> {
        return managerCallApi(
            call = {
                source.instance().getEvolutionChainPokemonAsync(url).await()
            })
    }
}