package br.com.fittogether.presentation.feature.signup.gender.viewmodel

import androidx.lifecycle.viewModelScope

import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.domain.usecase.signup.GetGenderUseCase
import br.com.fittogether.domain.usecase.signup.SetGenderUseCase
import br.com.fittogether.presentation.feature.signup.gender.intent.GenderIntent
import br.com.fittogether.presentation.feature.signup.gender.state.GenderState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GenderViewModel(
    private val preferenceController: PreferenceController,
    private val getGenderUseCase: GetGenderUseCase,
    private val setGenderUseCase: SetGenderUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(GenderState())
    val state = _state.asStateFlow()

    init {
        getGenders()
    }

    fun submitIntent(intent: GenderIntent) {
        when(intent) {
            is GenderIntent.Logout -> {
                preferenceController.clearUser()
                _state.update {
                    it.copy(navigateToStart = true)
                }
            }

            is GenderIntent.SelectGender -> {
                _state.update {
                    it.copy(
                        selectedGender = intent.gender
                    )
                }
            }

            is GenderIntent.SaveGender -> {
                saveGender()
            }
        }
    }

    private fun getGenders() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                },
                useCase = {
                    getGenderUseCase()
                },
                onSuccess = { response ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            genders = response.genders,
                            title = response.title
                        )
                    }
                },
                onError = {

                }
            )
        }
    }

    private fun saveGender() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(isRequesting = true)
                    }
                },
                useCase = {
                    setGenderUseCase(
                        userId = preferenceController.getUser()?.id,
                        genderId = state.value.selectedGender?.id
                    )
                },
                onSuccess = { response ->
                    preferenceController.setUser(user = response)

                    _state.update {
                        it.copy(
                            isRequesting = false,
                            navigateToGoal = true
                        )
                    }
                },
                onError = {
                    _state.update {
                        it.copy(
                            isRequesting = false
                        )
                    }
                }
            )
        }
    }
}