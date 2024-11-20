package br.com.fittogether.domain.model.signup

data class CreateUser(
    val email: String? = null,
    val username: String? = email,
    val name : String? = null,
    val cellphone: String? = null,
    val birthdate: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null
)