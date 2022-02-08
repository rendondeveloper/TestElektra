package com.rendonsoft.testelektra.home.module.fragment.detail.repository.repo

import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.PokemonDetail
import com.rendonsoft.testelektra.home.module.fragment.detail.usescases.remote.DetailRemoteUsesCases
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class DetailRepository(
    private val remoteSource : DetailRemoteUsesCases
) {
    suspend fun getDetailPokemon(name : String) : ResponseData<PokemonDetail?>{
        return remoteSource.getDetailPokemon(name)
    }
}