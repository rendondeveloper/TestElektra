package com.rendonsoft.testelektra.home.repository.evolutionLinerepository.model

data class Chain(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: SpeciesXX
)