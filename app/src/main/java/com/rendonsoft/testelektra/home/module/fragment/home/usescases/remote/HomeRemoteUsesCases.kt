package com.rendonsoft.testelektra.home.usescases.homeusescases.remote

import com.rendonsoft.testelektra.home.module.fragment.home.repository.model.PokemonData
import com.rendonsoft.testelektra.home.module.fragment.home.repository.retrofit.HomeApi
import com.rendonsoft.testelektra.utils.model.HomeRemoteData
import com.rendonsoft.testelektra.utils.retrofit.builder.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.managercall.ManagerCall
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class HomeRemoteUsesCases(private val remoteSource : RetrofitApp<HomeApi>) : ManagerCall(),
    HomeRemoteData {
    override suspend fun getAllPokemon(limit : Int) : ResponseData<PokemonData?>{
        return managerCallApi(
            call = { remoteSource.instance().getAllPokemonAsync(limit).await() }
        )
    }
}