package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.retrofit

import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EvolutionChainApi {
    @GET
    fun getEvolutionChainPokemonAsync(@Url url : String): Deferred<Response<PokemonEvolution>>
}