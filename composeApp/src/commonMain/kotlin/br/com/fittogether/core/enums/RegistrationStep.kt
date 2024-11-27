package br.com.fittogether.core.enums

enum class RegistrationStep(val value: String) {
    GENDER(value = "GENDER"),
    GOALS(value = "GOALS");

    companion object {
        fun findByValue(value: String?) : RegistrationStep {
            return RegistrationStep.entries.find { it.value == value } ?: GENDER
        }
    }
}