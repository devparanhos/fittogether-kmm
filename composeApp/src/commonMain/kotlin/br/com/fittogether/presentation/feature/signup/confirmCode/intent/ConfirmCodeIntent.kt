package br.com.fittogether.presentation.feature.signup.confirmCode.intent

sealed class ConfirmCodeIntent {
    data class UpdateCode(val code: String, val index: Int) : ConfirmCodeIntent()
    data object ValidateCode : ConfirmCodeIntent()
}