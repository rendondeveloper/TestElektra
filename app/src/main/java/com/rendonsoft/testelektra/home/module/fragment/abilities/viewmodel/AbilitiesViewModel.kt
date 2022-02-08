package com.rendonsoft.testelektra.home.module.fragment.abilities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.toAbilitiesView
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.repo.AbilitiesRepository
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilityPokemonView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AbilitiesViewModel (val repository : AbilitiesRepository) : ViewModel() {

    private val responseAbilityPokemonMLD = MutableLiveData<List<AbilityPokemonView>>()
    val responseAbilityPokemon : LiveData<List<AbilityPokemonView>>
        get () = responseAbilityPokemonMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getAbilitiesPokemon(namePokemon : String){
        GlobalScope.launch {
            val response = repository.getAbilitiesPokemon(namePokemon)
            GlobalScope.launch(Main) {
                if (response.sucess) {
                    response.data?.let {
                        responseAbilityPokemonMLD.value = it.toAbilitiesView()
                    }
                } else {
                    errorMLD.value = response.exception?.message
                }
            }
        }
    }




}