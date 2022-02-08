package com.rendonsoft.testelektra.home.module.fragment.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rendonsoft.testelektra.R
import com.rendonsoft.testelektra.home.module.fragment.home.adapter.PokemonsAdapter
import com.rendonsoft.testelektra.home.module.fragment.home.repository.repo.HomeRepository
import com.rendonsoft.testelektra.home.module.fragment.home.repository.retrofit.HomeApi
import com.rendonsoft.testelektra.home.usescases.homeusescases.datalocal.HomeLocalUsesCases
import com.rendonsoft.testelektra.home.usescases.homeusescases.datalocal.HomeSaveLocalUsesCases
import com.rendonsoft.testelektra.home.usescases.homeusescases.remote.HomeRemoteUsesCases
import com.rendonsoft.testelektra.home.module.fragment.home.viewmodel.HomeViewModel
import com.rendonsoft.testelektra.utils.extensions.getViewModel
import com.rendonsoft.testelektra.utils.model.ArgumentBundle.NAME_POKEMON
import com.rendonsoft.testelektra.utils.retrofit.builder.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.URL_BASE
import com.rendonsoft.testelektra.utils.room.instance.TestElektraDataBase
import com.rendonsoft.testelektra.utils.room.model.PokemonLocal
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by lazy {
        getViewModel {
            HomeViewModel(
                HomeRepository(
                    HomeLocalUsesCases(source = TestElektraDataBase.getRoomInstance(requireActivity())),
                    HomeSaveLocalUsesCases(source = TestElektraDataBase.getRoomInstance(requireActivity())),
                    HomeRemoteUsesCases(remoteSource = RetrofitApp.Build<HomeApi>().setHost(URL_BASE).setClass(HomeApi::class.java).setContext(requireContext()).builder())
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initData()
    }

    private fun initData() {
        viewModel.getAllPokemons()
    }

    private fun initObservers(){
        viewModel.responseLocalAllPokemons.observe(viewLifecycleOwner, resultData())
        viewModel.error.observe(viewLifecycleOwner, resultError())
    }

    private fun resultData() : (List<PokemonLocal>) -> Unit = { data ->
        val adapterData =  PokemonsAdapter(data, ::listenerAdapter)
        rvPokemons.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterData
        }
    }

    private fun listenerAdapter(pokemonLocal: PokemonLocal) {
        val bundle : Bundle = Bundle().apply {
            putString(NAME_POKEMON, pokemonLocal.name)
        }
        findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun resultError():(String?) -> Unit = {
            Toast.makeText(requireContext(), "Ocurrio un erro", Toast.LENGTH_LONG).show()
    }
}

