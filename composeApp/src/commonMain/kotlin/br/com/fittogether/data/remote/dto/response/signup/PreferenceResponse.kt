package br.com.fittogether.data.remote.dto.response.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreferenceResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("startAge")
    val startAge: Int? = null,

    @SerialName("endAge")
    val endAge: Int? = null,

    @SerialName("radiusDistance")
    val radiusDistance: Int? = null,

    @SerialName("genders")
    val genders: List<GenderResponse>? = null
)