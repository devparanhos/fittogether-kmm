package br.com.fittogether.presentation.feature.signup.createUser.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.domain.repository.signup.SignupRepository
import br.com.fittogether.domain.usecase.signup.CreateUserUseCase
import br.com.fittogether.presentation.feature.signup.createUser.intent.CreateUserIntent
import br.com.fittogether.presentation.feature.signup.createUser.state.CreateUserState
import br.com.fittogether.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive

class CreateUserViewModel(
    email: String,
    private val createUserUseCase: CreateUserUseCase,
    private val preferences: PreferenceController
) : BaseViewModel() {
    private val _state = MutableStateFlow(CreateUserState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(email = email)
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
        }
    }

    private fun saveUser() {
        viewModelScope.launch {
            callUseCase(
                prepareUi = {

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
                onError = {

                }
            )
        }
    }

}