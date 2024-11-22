package br.com.fittogether.presentation.feature.signup.createUser.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.domain.usecase.signup.CreateUserUseCase
import br.com.fittogether.presentation.feature.signup.createUser.intent.CreateUserIntent
import br.com.fittogether.presentation.feature.signup.createUser.state.CreateUserState
import br.com.fittogether.presentation.viewmodel.BaseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val preferences: PreferenceController
) : BaseViewModel() {
    private val _state = MutableStateFlow(CreateUserState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(email = preferences.getEmailRegistration())
        }
    }

    fun submitIntent(intent: CreateUserIntent) {
        when(intent) {
            is CreateUserIntent.UpdateBirthdate -> {
                _state.update {
                    it.copy(birthdate = intent.birthdate)
                }
            }

            is CreateUserIntent.UpdateCellphone -> {
                _state.update {
                    it.copy(cellphone = intent.cellphone)
                }
            }

            is CreateUserIntent.UpdateConfirmPassword -> {
                _state.update {
                    it.copy(confirmPassword = intent.confirmPassword)
                }
            }

            is CreateUserIntent.UpdateName -> {
                _state.update {
                    it.copy(name = intent.name)
                }
            }

            is CreateUserIntent.UpdatePassword -> {
                _state.update {
                    it.copy(password = intent.password)
                }
            }

            is CreateUserIntent.SaveUser -> {
                saveUser()
            }

            is CreateUserIntent.OpenCloseDialog -> {
                updateDialog()
            }
        }
    }

    private fun saveUser() {
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
                    createUserUseCase(
                        email = state.value.email,
                        name = state.value.name,
                        password = state.value.password,
                        cellphone = state.value.cellphone,
                        birthdate = state.value.birthdate,
                        confirmPassword = state.value.confirmPassword
                    )
                },
                onSuccess = {
                    preferences.setUser(token = it.accessToken)
                },
                onError = { error ->
                    _state.update { data ->
                        data.copy(
                            isRequesting = false,
                            openDialog = true,
                            error = error,
                            fieldErrors = error?.errors?.let { fields ->
                                fields.associate { it.field to it.message }
                            }
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

}