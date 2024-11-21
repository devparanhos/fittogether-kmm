package br.com.fittogether.domain.model.user

import br.com.fittogether.core.enums.RegistrationStep

data class User(
    val accessToken: String? = null,
    val registrationStep: RegistrationStep? = null
)