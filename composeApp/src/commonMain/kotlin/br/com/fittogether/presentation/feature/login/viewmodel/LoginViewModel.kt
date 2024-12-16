package br.com.fittogether.presentation.feature.login.viewmodel

import androidx.lifecycle.viewModelScope

import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.core.enums.RegistrationStep
import br.com.fittogether.domain.usecase.login.LoginUseCase
import br.com.fittogether.presentation.feature.login.intent.LoginIntent
import br.com.fittogether.presentation.feature.login.state.LoginState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val preferenceController: PreferenceController,
    private val refreshHttpClient: () -> Unit
) : BaseViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(email = preferenceController.getEmailRegistration())
        }
    }

    fun submitIntent(intent: LoginIntent) {
        when(intent) {
            is LoginIntent.UpdateEmail -> {
                _state.update {
                    it.copy(email = intent.email)
                }
            }

            is LoginIntent.UpdatePassword -> {
                _state.update {
                    it.copy(password = intent.password)
                }
            }

            is LoginIntent.OpenCloseDialog -> {
                _state.update {
                    it.copy(
                        openDialog = !state.value.openDialog
                    )
                }
            }

            is LoginIntent.Login -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(isRequesting = true)
                    }
                },
                useCase = {
                    loginUseCase(
                        email = state.value.email,
                        password = state.value.password
                    )
                },
                onSuccess = { response ->
                    preferenceController.setToken(token = response.accessToken)
                    preferenceController.setUser(user = response)

                    refreshHttpClient()

                    when(response.registrationStep) {
                        RegistrationStep.GENDER -> {
                            _state.update {
                                it.copy(
                                    navigateToGender = true,
                                    isRequesting = false
                                )
                            }
                        }

                        RegistrationStep.GOALS -> {
                            _state.update {
                                it.copy(
                                    navigateToGoals = true,
                                    isRequesting = false
                                )
                            }
                        }

                        else -> {

                        }
                    }
                },
                onError = { error ->
                    _state.update { state ->
                        state.copy(
                            isRequesting = false,
                            error = error,
                            openDialog = true,
                            fieldErrors = error?.errors?.let { fields ->
                                fields.associate { it.field to it.message }
                            }
                        )
                    }
                }
            )
        }
    }
}