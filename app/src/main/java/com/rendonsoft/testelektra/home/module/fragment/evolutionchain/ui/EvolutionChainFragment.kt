package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rendonsoft.testelektra.databinding.FragmentEvolutionChainBinding
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.adapter.EvolutionChainAdapter
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model.PokemonEvolutionChainView
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.repo.EvolutionChainRepository
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.retrofit.EvolutionChainApi
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.usescases.remote.EvolutionChainRemoteUsesCases
import com.rendonsoft.testelektra.home.module.fragment.evolutionchain.viewmodel.EvolutionChainViewModel
import com.rendonsoft.testelektra.utils.extensions.getViewModel
import com.rendonsoft.testelektra.utils.extensions.log
import com.rendonsoft.testelektra.utils.model.ArgumentBundle.NAME_POKEMON
import com.rendonsoft.testelektra.utils.model.ArgumentBundle.URL_EVOLUTION_CHAIN
import com.rendonsoft.testelektra.utils.retrofit.builder.RetrofitApp

class EvolutionChainFragment : Fragment() {

    private lateinit var binding : FragmentEvolutionChainBinding

    private val  viewModel : EvolutionChainViewModel by lazy {
        getViewModel {
            EvolutionChainViewModel(
                EvolutionChainRepository(
                    remoteSource = EvolutionChainRemoteUsesCases(
                        source = RetrofitApp.Build<EvolutionChainApi>().setHost(requireArguments().getString(URL_EVOLUTION_CHAIN, "")).setClass(EvolutionChainApi::class.java).setContext(requireContext()).builder()
                    )
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEvolutionChainBinding.inflate(inflater, container, false)
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
            titleEvolutionChain = requireArguments().getString(NAME_POKEMON, "")
        }
    }

    private fun initObservers() {
        viewModel.responseEvolutionChainPokemon.observe(viewLifecycleOwner, handleEvolutionChain())
    }

    private fun initData() {
        val url = requireArguments().getString(URL_EVOLUTION_CHAIN, "")
        viewModel.getEvolutionChainPokemon(url)
    }

    private fun handleEvolutionChain(): (List<PokemonEvolutionChainView>) -> Unit = { data ->
        val adapterData =  EvolutionChainAdapter(data, ::listenerAdapter)
        binding.rvEvolutionChain.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterData
        }
    }

    private fun listenerAdapter(item: PokemonEvolutionChainView) {
        //TODO execute save favorite
        "Click -> $item".log()
    }
}