package br.com.fittogether.presentation.feature.signup.verifyEmail.viewmodel

import androidx.lifecycle.viewModelScope

import br.com.fittogether.domain.usecase.signup.VerifyEmailUseCase
import br.com.fittogether.presentation.feature.signup.verifyEmail.intent.VerifyEmailIntent
import br.com.fittogether.presentation.feature.signup.verifyEmail.state.VerifyEmailState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerifyEmailViewModel(
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(VerifyEmailState())
    val state = _state.asStateFlow()

    fun submitIntent(intent: VerifyEmailIntent) {
        when(intent) {
            is VerifyEmailIntent.UpdateEmail -> {
                _state.update {
                    it.copy(email = intent.email)
                }
            }

            is VerifyEmailIntent.SendEmail -> {
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
                        onSuccess = {
                            _state.update {
                                it.copy(
                                    isRequesting = false,
                                    navigateToConfirmCode = true
                                )
                            }
                        },
                        onError = { error ->
                            _state.update {
                                it.copy(
                                    isRequesting = false,
                                    error = error
                                )
                            }
                        }
                    )
                }
            }

            is VerifyEmailIntent.Clear -> {
                clearState(state = _state, defaultValue = VerifyEmailState())
            }
        }
    }
}