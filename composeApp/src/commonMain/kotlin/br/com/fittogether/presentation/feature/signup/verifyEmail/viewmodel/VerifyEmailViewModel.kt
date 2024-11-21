package br.com.fittogether.presentation.feature.signup.verifyEmail.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.core.enums.UserStatus
import br.com.fittogether.data.remote.wrapper.ApiError

import br.com.fittogether.domain.usecase.signup.VerifyEmailUseCase
import br.com.fittogether.presentation.common.dialogs.DialogType
import br.com.fittogether.presentation.feature.signup.verifyEmail.intent.VerifyEmailIntent
import br.com.fittogether.presentation.feature.signup.verifyEmail.state.VerifyEmailState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerifyEmailViewModel(
    private val preferences: PreferenceController,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(VerifyEmailState())
    val state = _state.asStateFlow()

    fun submitIntent(intent: VerifyEmailIntent) {
        when(intent) {
            is VerifyEmailIntent.UpdateEmail -> {
                updateEmail(email = intent.email)
            }

            is VerifyEmailIntent.OpenCloseDialog -> {
                updateDialog()
            }

            is VerifyEmailIntent.SendEmail -> {
                verifyEmail()
            }

            is VerifyEmailIntent.Clear -> {
                clearState(state = _state, defaultValue = VerifyEmailState())
            }
        }
    }

    private fun verifyEmail() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(isRequesting = true, error = null)
                    }
                },
                useCase = {
                    verifyEmailUseCase(email = state.value.email)
                },
                onSuccess = { response ->
                    when(response.status) {
                        null, UserStatus.NOT_FOUND, UserStatus.IN_VALIDATION,  -> {
                            if (response.sendingCode == true) {
                                preferences.setEmailRegistration(email = state.value.email)

                                _state.update {
                                    it.copy(
                                        isRequesting = false,
                                        navigateToConfirmCode = true
                                    )
                                }
                            } else {
                                _state.update {
                                    it.copy(
                                        isRequesting = false,
                                        error = ApiError(
                                            message = "Não foi possível enviar o código. Tente novamente!"
                                        ),
                                        openDialog = true
                                    )
                                }
                            }
                        }

                        UserStatus.REGISTRATION -> {
                            preferences.setEmailRegistration(email = state.value.email)

                            _state.update {
                                it.copy(
                                    isRequesting = false,
                                    navigateToRegistration = true
                                )
                            }
                        }

                        UserStatus.CREATED -> {
                            preferences.setEmailRegistration(email = state.value.email)

                            _state.update {
                                it.copy(
                                    isRequesting = false,
                                    openDialog = true,
                                    dialogType = DialogType.Email
                                )
                            }
                        }
                    }
                },
                onError = { error ->
                    _state.update {
                        it.copy(
                            isRequesting = false,
                            error = error,
                            openDialog = true
                        )
                    }
                }
            )
        }
    }

    private fun updateDialog() {
        _state.update {
            it.copy(
                openDialog = !_state.value.openDialog
            )
        }
    }

    private fun updateEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }
}