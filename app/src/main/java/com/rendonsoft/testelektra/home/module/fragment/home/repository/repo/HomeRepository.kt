package com.rendonsoft.testelektra.home.module.fragment.home.repository.repo

import com.rendonsoft.testelektra.home.module.fragment.home.repository.model.PokemonData
import com.rendonsoft.testelektra.home.module.fragment.home.repository.model.getDataLocal
import com.rendonsoft.testelektra.home.usescases.homeusescases.datalocal.HomeLocalUsesCases
import com.rendonsoft.testelektra.home.usescases.homeusescases.datalocal.HomeSaveLocalUsesCases
import com.rendonsoft.testelektra.home.usescases.homeusescases.remote.HomeRemoteUsesCases
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.ResponseData
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal

class HomeRepository(
    private val getLocalSource : HomeLocalUsesCases,
    private val saveLocalSource : HomeSaveLocalUsesCases,
    private val remoteSource : HomeRemoteUsesCases
) {
    private val LIMIT_OF_POKEMONS = 151

    suspend fun getAllPokemons() : ResponseData<List<PokemonLocal>>{
        val responseGeneral = ResponseData<List<PokemonLocal>>()
        val localList = getLocalSource.getAllPokemon()

        return if(localList.isEmpty()){
            val response = remoteSource.getAllPokemon(LIMIT_OF_POKEMONS)
            saveData(response)
            responseGeneral.apply {
                sucess = response.sucess
                data = response.data?.getDataLocal() ?: listOf()
                exception = response.exception
            }
        }else{
            responseGeneral.apply {
                sucess = true
                data = localList
            }
        }
    }

    private fun saveData(response : ResponseData<PokemonData?>){
        if(response.sucess) {
            response.data?.let {
                saveLocalSource.addPokemon(it.getDataLocal())
            }
        }
    }

}