package br.com.fittogether.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val email: String? = null,
    val username: String? = "teste",
    val name : String? = null,
    val cellphone: String? = null,
    @SerialName("birth_date")
    val birthdate: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null
)