package com.rendonsoft.testelektra.home.module.fragment.abilities.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rendonsoft.testelektra.databinding.FragmentAbilitiesBinding
import com.rendonsoft.testelektra.home.module.fragment.abilities.adapter.AbilitiesAdapter
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilityPokemonView
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.repo.AbilitiesRepository
import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.retrofit.AbilityApi
import com.rendonsoft.testelektra.home.module.fragment.abilities.usescases.remote.AbilitiesRemoteUsesCases
import com.rendonsoft.testelektra.home.module.fragment.abilities.viewmodel.AbilitiesViewModel
import com.rendonsoft.testelektra.utils.extensions.getViewModel
import com.rendonsoft.testelektra.utils.model.ArgumentBundle.NAME_POKEMON
import com.rendonsoft.testelektra.utils.retrofit.build.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.URL_BASE

class AbilitiesFragment : Fragment() {

    private lateinit var binding: FragmentAbilitiesBinding

    private val viewModel : AbilitiesViewModel by lazy {
        getViewModel {
            AbilitiesViewModel(
                AbilitiesRepository(
                    remoteSource = AbilitiesRemoteUsesCases(
                        source = RetrofitApp.Build<AbilityApi>().setHost(URL_BASE).setClass(
                            AbilityApi::class.java).setContext(requireContext()).builder()
                    )
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbilitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initData()
    }

    private fun initView() {
        binding.apply {
            titleAbilities = requireArguments().getString(NAME_POKEMON, "")
        }
    }

    private fun initData() {
        val pokemonName = requireArguments().getString(NAME_POKEMON, "")
        viewModel.getAbilitiesPokemon(pokemonName)
    }

    private fun initObservers() {
        viewModel.responseAbilityPokemon.observe(viewLifecycleOwner, handleAbilities())
    }

    private fun handleAbilities():  (List<AbilityPokemonView>) -> Unit = { data ->
        val adapterData =  AbilitiesAdapter(data, ::listenerAdapter)
        binding.apply {
            rvAbilities.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = adapterData
            }
        }
    }

    private fun listenerAdapter(abilityPokemonView: AbilityPokemonView) {}
}