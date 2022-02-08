package com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model

import com.rendonsoft.testelektra.home.module.fragment.abilities.repository.model.AbilityX

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)