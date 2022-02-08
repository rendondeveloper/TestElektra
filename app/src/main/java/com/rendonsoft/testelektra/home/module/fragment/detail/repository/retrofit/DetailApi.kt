package com.rendonsoft.testelektra.home.module.fragment.detail.repository.retrofit

import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.retrofit.AbilityConfig
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.PokemonDetail
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET(DetailConfig.GET_DETAIL_POKEMON)
    fun getDetailPokemonAsync(@Path("name") name: String): Deferred<Response<PokemonDetail>>
}