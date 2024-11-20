package br.com.fittogether.presentation.feature.signup.createUser.state

data class CreateUserState(
    val email: String = "",
    val cellphone: String = "",
    val name: String = "",
    val birthdate: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)