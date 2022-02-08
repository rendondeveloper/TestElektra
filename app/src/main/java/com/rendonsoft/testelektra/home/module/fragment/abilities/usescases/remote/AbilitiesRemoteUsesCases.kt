package com.rendonsoft.testelektra.home.module.fragment.abilities.usescases.remote

import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.retrofit.AbilityApi
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import com.rendonsoft.testelektra.utils.model.AbilitiesRemoteData
import com.rendonsoft.testelektra.utils.retrofit.builder.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.managercall.ManagerCall
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData

class AbilitiesRemoteUsesCases(private val source : RetrofitApp<AbilityApi>) : ManagerCall(),
    AbilitiesRemoteData {

    override suspend fun getAbilitiesPokemon(name: String): ResponseData<AbilitiesPokemon?> {
        return managerCallApi(
            call = {
                source.instance().getAbilitiesPokemonAsync(name).await()
            })
    }
}