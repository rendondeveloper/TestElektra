package com.rendonsoft.testelektra.home.module.fragment.abilities.repository.repo

import com.rendonsoft.testelektra.home.module.fragment.abilities.usescases.remote.AbilitiesRemoteUsesCases
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class AbilitiesRepository(
    private val remoteSource : AbilitiesRemoteUsesCases
) {
    suspend fun getAbilitiesPokemon(name : String) : ResponseData<AbilitiesPokemon?>{
        return remoteSource.getAbilitiesPokemon(name)
    }
}