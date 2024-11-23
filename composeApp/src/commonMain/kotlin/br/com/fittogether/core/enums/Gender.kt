package br.com.fittogether.core.enums

enum class Gender(val value: String, val label: String) {
    MALE(value = "MALE", label = "Masculino"),
    FEMALE(value = "FEMALE", label = "Feminino"),
    NONBINARY(value = "NONBINARY", label = "Não binário");

    companion object {
        fun findByValue(value: String?) : Gender {
            return Gender.entries.find { it.value == value } ?: NONBINARY
        }
    }
}