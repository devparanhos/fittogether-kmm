package br.com.fittogether.data.remote.dto.request.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    @SerialName("email")
    val email: String? = null,

    @SerialName("username")
    val username: String? = null,

    @SerialName("name")
    val name : String? = null,

    @SerialName("cellphone")
    val cellphone: String? = null,

    @SerialName("birth_date")
    val birthdate: String? = null,

    @SerialName("password")
    val password: String? = null,

    @SerialName("confirmPassword")
    val confirmPassword: String? = null
)