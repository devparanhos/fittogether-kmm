package br.com.fittogether.data.remote.dto.response.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGendersResponse(
    @SerialName("title")
    val title: String? = null,

    @SerialName("genders")
    val genders : List<GenderResponse>? = null
)