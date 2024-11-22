package br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.domain.model.error.ApiError
import br.com.fittogether.domain.usecase.signup.ValidateCodeUseCase
import br.com.fittogether.domain.usecase.signup.VerifyEmailUseCase
import br.com.fittogether.presentation.feature.signup.confirmCode.intent.ConfirmCodeIntent
import br.com.fittogether.presentation.feature.signup.confirmCode.state.ConfirmCodeState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfirmCodeViewModel(
    private val preferences: PreferenceController,
    private val validateCodeUseCase: ValidateCodeUseCase,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(ConfirmCodeState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(email = preferences.getEmailRegistration())
        }

        startCountdown()
    }

    fun submitIntent(intent: ConfirmCodeIntent) {
        when(intent) {
            is ConfirmCodeIntent.UpdateCode -> {
                updateCodes(newCode = intent.code, i = intent.index)
            }

            is ConfirmCodeIntent.ValidateCode -> {
                validateCode()
            }

            is ConfirmCodeIntent.OpenCloseDialog -> {
                updateDialog()
            }

            is ConfirmCodeIntent.ResendCode -> {
                resendCode()
            }
        }
    }

    private fun updateCodes(newCode: String, i: Int) {
        val newCodes = mutableListOf("", "", "", "", "", "")

        state.value.listCodes.forEachIndexed { index, code ->
            if(i == index) {
                newCodes[index] = newCode
            } else {
                newCodes[index] = code
            }
        }

        _state.update {
            it.copy(
                listCodes = newCodes,
                fieldErrors = it.fieldErrors?.minus("code")
            )
        }
    }

    private fun startCountdown() {
        viewModelScope.launch {
            while (_state.value.secondsLeft > 0) {
                delay(1000L)
                _state.update { it.copy(secondsLeft = _state.value.secondsLeft - 1) }
            }

            _state.update {
                it.copy(
                    canResendCode = true
                )
            }
        }
    }

    private fun validateCode() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(isVerifyingCode = true)
                    }
                },
                useCase = {
                    validateCodeUseCase(
                        email = state.value.email,
                        code = state.value.listCodes.joinToString(separator = "")
                    )
                },
                onSuccess = { response ->
                    _state.update {
                        it.copy(
                            isVerifyingCode = false,
                            navigateToCreateUser = true
                        )
                    }
                },
                onError = { error ->
                    if (error != null) {
                        _state.update { data ->
                            data.copy(
                                isVerifyingCode = false,
                                error = error,
                                fieldErrors = error.errors?.associate { it.field to it.message },
                                openDialog = true
                            )
                        }
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

    private fun resendCode() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {
                    _state.update {
                        it.copy(
                            isResendingCode = true,
                            canResendCode = false
                        )
                    }
                },
                useCase = {
                    verifyEmailUseCase(email = preferences.getEmailRegistration())
                },
                onSuccess = { response ->
                    if (response.sendingCode == true) {
                        _state.update {
                            it.copy(
                                isResendingCode = false,
                                secondsLeft = 100
                            )
                        }

                        startCountdown()
                    } else {
                        _state.update {
                            it.copy(
                                canResendCode = true,
                                isResendingCode = false,
                                error = ApiError(
                                    message = "Não foi possível enviar o código. Tente novamente!"
                                ),
                                openDialog = true
                            )
                        }
                    }
                },
                onError = { error ->
                    _state.update { data ->
                        data.copy(
                            canResendCode = true,
                            isResendingCode = false,
                            error = error,
                            openDialog = true
                        )
                    }
                }
            )
        }
    }
}