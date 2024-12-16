package br.com.fittogether.domain.model.signup

import br.com.fittogether.domain.model.goal.Goal

data class GetGoal(
    val title: String? = null,
    val goals: List<Goal>? = null
)