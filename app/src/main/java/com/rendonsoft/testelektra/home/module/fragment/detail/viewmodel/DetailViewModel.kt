package com.rendonsoft.testelektra.home.module.fragment.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.PokemonDetailView
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.toView
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.repo.DetailRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel (val repository : DetailRepository) : ViewModel() {

    private val responseDetailPokemonMLD = MutableLiveData<PokemonDetailView>()
    val responseDetailPokemon : LiveData<PokemonDetailView>
        get () = responseDetailPokemonMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getDetailPokemon(namePokemon : String){
        GlobalScope.launch {
            val response = repository.getDetailPokemon(namePokemon)
            GlobalScope.launch(Main) {
                if (response.sucess) {
                    response.data?.let {
                        responseDetailPokemonMLD.value = it.toView(namePokemon)
                    }
                } else {
                    errorMLD.value = response.exception?.message
                }
            }
        }
    }




}