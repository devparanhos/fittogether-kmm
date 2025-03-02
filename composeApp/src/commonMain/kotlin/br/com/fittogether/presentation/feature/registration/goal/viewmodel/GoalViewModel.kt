package br.com.fittogether.presentation.feature.registration.goal.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.domain.model.goal.Goal
import br.com.fittogether.domain.usecase.signup.GetGoalUseCase
import br.com.fittogether.domain.usecase.signup.SetGoalsUseCase
import br.com.fittogether.presentation.feature.registration.goal.intent.GoalIntent
import br.com.fittogether.presentation.feature.registration.goal.state.GoalState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalViewModel(
    private val preferences: PreferenceController,
    private val getGoalsUseCase: GetGoalUseCase,
    private val setGoalsUseCase : SetGoalsUseCase
) : BaseViewModel() {
    private val _state =  MutableStateFlow(GoalState())
    val state = _state.asStateFlow()

    init {
        getGoals()
    }

    fun submitIntent(intent: GoalIntent) {
        when(intent) {
            is GoalIntent.Logout -> {
                preferences.clearUser()
                _state.update {
                    it.copy(navigateToStart = true)
                }
            }

            is GoalIntent.SelectGoal -> {
                setSelectedGoal(goal = intent.goal)
            }

            is GoalIntent.SetGoals -> {
                setGoals()
            }
        }
    }

    private fun getGoals() {
        viewModelScope.launch {
            callUseCase(
                useCase = {
                    getGoalsUseCase()
                },
                onSuccess = { response ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            title = response.title,
                            goals = response.goals
                        )
                    }
                },
                onError = { error ->
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            )
        }
    }

    private fun setSelectedGoal(goal: Goal) {
        _state.update { currentState ->
            currentState.copy(
                goalsSelected = currentState.goalsSelected.toMutableList().apply {
                    if (contains(goal)) {
                        remove(goal)
                    } else {
                        add(goal)
                    }
                }
            )
        }
    }

    private fun setGoals() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(
                            isRequesting = true
                        )
                    }
                },
                useCase = {
                    setGoalsUseCase(
                        userId = preferences.getUser()?.id,
                        goals = state.value.goals
                    )
                },
                onSuccess = { response ->

                },
                onError = { error ->

                }
            )
        }
    }
}