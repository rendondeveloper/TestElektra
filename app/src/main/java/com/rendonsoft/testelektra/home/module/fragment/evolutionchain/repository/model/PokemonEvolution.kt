package com.rendonsoft.testelektra.home.module.fragment.evolutionchain.repository.model

import com.rendonsoft.testelektra.home.repository.evolutionLinerepository.model.Chain
import com.rendonsoft.testelektra.home.repository.evolutionLinerepository.model.EvolvesTo

data class PokemonEvolution(
    val baby_trigger_item: Any,
    val chain: Chain,
    val id: Int
)


fun PokemonEvolution.toEvolutionChain() : List<PokemonEvolutionChainView>{
    val listToChainView : MutableList<PokemonEvolutionChainView> = mutableListOf()
    buildEvolutionChain(this.chain.evolves_to, listToChainView)
    return listToChainView.toList()
}

fun buildEvolutionChain(evolutionItem : List<EvolvesTo>, listToChainView : MutableList<PokemonEvolutionChainView>){
    evolutionItem.forEach { item ->
        val poke = PokemonEvolutionChainView(
            item.species.name
        )
        listToChainView.add(poke)
        if(item.evolves_to.isNotEmpty()){
            buildEvolutionChain(item.evolves_to, listToChainView)
        }
    }
}




