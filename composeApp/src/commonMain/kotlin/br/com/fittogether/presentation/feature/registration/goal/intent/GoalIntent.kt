package br.com.fittogether.presentation.feature.registration.goal.intent

import br.com.fittogether.domain.model.goal.Goal

sealed class GoalIntent {
    data class SelectGoal(val goal: Goal) : GoalIntent()
    data object Logout : GoalIntent()
    data object SetGoals : GoalIntent()
}