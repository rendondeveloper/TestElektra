package com.rendonsoft.testelektra.home.module.fragment.home.repository.retrofit

import com.rendonsoft.testelektra.home.module.fragment.home.repository.model.PokemonData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET(HomeConfig.GET_ALL_POKEMON)
    fun getAllPokemonAsync(@Query("limit") limit: Int): Deferred<Response<PokemonData>>
}