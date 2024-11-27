package br.com.fittogether.data.remote.dto.request.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SetGenderRequest(
    @SerialName("gender_id")
    val genderId: Int? = null
)