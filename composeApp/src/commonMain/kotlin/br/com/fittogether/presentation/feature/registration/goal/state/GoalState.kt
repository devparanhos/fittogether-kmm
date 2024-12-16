package br.com.fittogether.presentation.feature.registration.goal.state

import br.com.fittogether.domain.model.goal.Goal

data class GoalState(
    val navigateToStart: Boolean = false,
    val isLoading: Boolean = true,
    val goals : List<Goal>? = null,
    val title: String? = "",
    val goalsSelected : MutableList<Goal> = mutableListOf()
)