package br.com.fittogether.presentation.feature.signup.gender.viewmodel

import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.presentation.feature.signup.gender.intent.GenderIntent
import br.com.fittogether.presentation.feature.signup.gender.state.GenderState
import br.com.fittogether.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GenderViewModel(
    private val preferenceController: PreferenceController
) : BaseViewModel() {

    private val _state = MutableStateFlow(GenderState())
    val state = _state.asStateFlow()

    fun submitIntent(intent: GenderIntent) {
        when(intent) {
            is GenderIntent.Logout -> {
                preferenceController.clearUser()
                _state.update {
                    it.copy(navigateToStart = true)
                }
            }
        }
    }
}