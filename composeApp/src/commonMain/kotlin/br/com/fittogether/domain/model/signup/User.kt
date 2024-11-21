package br.com.fittogether.domain.model.signup

import br.com.fittogether.core.enums.RegistrationStep

data class User(
    val accessToken: String,
    val registrationStep: RegistrationStep
)