package br.com.fittogether.presentation.feature.signup.verifyEmail.viewmodel

import androidx.lifecycle.viewModelScope

import br.com.fittogether.domain.usecase.signup.VerifyEmailUseCase
import br.com.fittogether.presentation.feature.signup.verifyEmail.intent.VerifyEmailIntents
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

    fun submitIntent(intent: VerifyEmailIntents) {
        when(intent) {
            is VerifyEmailIntents.UpdateEmail -> {
                _state.update {
                    it.copy(email = intent.email)
                }
            }

            is VerifyEmailIntents.SendEmail -> {
                viewModelScope.launch {
                    callUseCase(
                        prepareUi = {
                            _state.update {
                                it.copy(isRequesting = true, error = null)
                            }
                        },
                        useCase = {
                            verifyEmailUseCase.verifyEmail(email = state.value.email)
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
        }
    }
}