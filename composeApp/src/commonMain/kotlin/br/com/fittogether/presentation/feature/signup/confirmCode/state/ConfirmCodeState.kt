package br.com.fittogether.presentation.feature.signup.confirmCode.state

data class ConfirmCodeState(
    val email: String = "",
    val listCodes : MutableList<String> = mutableListOf("", "", "", "", "", ""),
    val secondsLeft: Int = 50,
    val canResendCode: Boolean = false,
    val navigateToCreateUser: Boolean = false
)