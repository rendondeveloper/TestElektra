package com.rendonsoft.testelektra.home.module.fragment.abilities.repository.retrofit

import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AbilityApi {
    @GET(AbilityConfig.GET_ABILITY_POKEMON)
    fun getAbilitiesPokemonAsync(@Path("name") name: String): Deferred<Response<AbilitiesPokemon>>
}