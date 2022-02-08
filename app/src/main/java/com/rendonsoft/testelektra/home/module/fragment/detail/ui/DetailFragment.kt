package com.rendonsoft.testelektra.home.module.fragment.detail.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rendonsoft.testelektra.R
import com.rendonsoft.testelektra.databinding.FragmentDetailBinding
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.model.PokemonDetailView
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.repo.DetailRepository
import com.rendonsoft.testelektra.home.module.fragment.detail.repository.retrofit.DetailApi
import com.rendonsoft.testelektra.home.module.fragment.detail.usescases.remote.DetailRemoteUsesCases
import com.rendonsoft.testelektra.home.module.fragment.detail.viewmodel.DetailViewModel
import com.rendonsoft.testelektra.utils.extensions.getViewModel
import com.rendonsoft.testelektra.utils.model.ArgumentBundle.NAME_POKEMON
import com.rendonsoft.testelektra.utils.model.ArgumentBundle.URL_EVOLUTION_CHAIN
import com.rendonsoft.testelektra.utils.retrofit.builder.RetrofitApp
import com.rendonsoft.testelektra.utils.retrofit.model.dataclass.URL_BASE

class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding

    private val viewModel : DetailViewModel by lazy {
        getViewModel {
            DetailViewModel(
                DetailRepository(
                       DetailRemoteUsesCases( source = RetrofitApp.Build<DetailApi>().setHost(URL_BASE).setClass(
                           DetailApi::class.java).setContext(requireContext()).builder()
                    )
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
        initData()
    }

    private fun initData() {
        val data = requireArguments().getString(NAME_POKEMON) ?: ""
        viewModel.getDetailPokemon(data)
    }

    private fun initObservers() {
        viewModel.responseDetailPokemon.observe(viewLifecycleOwner, handleDetail())
    }

    private fun handleDetail() : (PokemonDetailView) -> Unit = {
        binding.apply {
            item = it
        }
    }

    private fun initActions(){
        binding.apply {
            btnAbilities.setOnClickListener(::navigationToAbilities)
            btnEvolutionChain.setOnClickListener(::navigationToEvolutionChain)
        }
    }

    private fun navigationToAbilities(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_abilitiesFragment, requireArguments())
    }

    private fun navigationToEvolutionChain(view: View) {
        val bundle : Bundle = Bundle().apply {
            putString(URL_EVOLUTION_CHAIN , binding.item?.evolutionUrl ?: "")
            putString(NAME_POKEMON , binding.item?.name ?: "")
        }
        Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_evolutionChainFragment, bundle)
    }
}