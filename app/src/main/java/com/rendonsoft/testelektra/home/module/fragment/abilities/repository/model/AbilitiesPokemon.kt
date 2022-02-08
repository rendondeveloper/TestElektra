package com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model

data class AbilitiesPokemon(
    val abilities: List<Ability>
)

fun AbilitiesPokemon.toAbilitiesView() : List<AbilityPokemonView> =
    this.abilities.map { item -> AbilityPokemonView(item.ability.name) }.toList()
