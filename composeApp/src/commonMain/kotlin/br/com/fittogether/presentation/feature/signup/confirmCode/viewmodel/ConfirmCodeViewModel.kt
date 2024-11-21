package br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel

import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.viewModelScope
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.domain.usecase.signup.ValidateCodeUseCase
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
    private val validateCodeUseCase: ValidateCodeUseCase
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

        _state.update { it.copy(listCodes = newCodes) }
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

                },
                useCase = {
                    validateCodeUseCase(
                        email = state.value.email,
                        code = state.value.listCodes.joinToString(separator = "")
                    )
                },
                onSuccess = {
                    _state.update {
                        it.copy(
                            navigateToCreateUser = true
                        )
                    }
                },
                onError = {

                }
            )
        }
    }
}