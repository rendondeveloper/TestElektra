package com.rendonsoft.testelektra.home.module.fragment.detail.usescases.remote

import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.PokemonDetail
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.retrofit.DetailApi
import com.rendonsoft.testelektra.utils.model.DetailRemoteData
import com.rendonsoft.testelektra.utils.retrofit.build.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.managercall.ManagerCall
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class DetailRemoteUsesCases(private val source : RetrofitApp<DetailApi>) : ManagerCall(),
    DetailRemoteData {
    override suspend fun getDetailPokemon(name: String): ResponseData<PokemonDetail?> {
        return managerCallApi(
            call = {
                source.instance().getDetailPokemonAsync(name).await()
            })
    }
}