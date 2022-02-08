package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.toEvolutionChain
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.repo.EvolutionChainRepository
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolutionChainView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EvolutionChainViewModel (val repository : EvolutionChainRepository) : ViewModel() {

    private val responseEvolutionChainPokemonMLD = MutableLiveData<List<PokemonEvolutionChainView>>()
    val responseEvolutionChainPokemon : LiveData<List<PokemonEvolutionChainView>>
        get () = responseEvolutionChainPokemonMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getEvolutionChainPokemon(url : String){
        GlobalScope.launch {
            val response = repository.getEvolutionChainPokemon(url)
            GlobalScope.launch(Main) {
                if (response.sucess) {
                    response.data?.let {
                        responseEvolutionChainPokemonMLD.value = it.toEvolutionChain()
                    }
                } else {
                    errorMLD.value = response.exception?.message
                }
            }
        }
    }




}