package br.com.fittogether.core.enums

enum class UserStatus(val value: String) {
    NOT_FOUND(value = "NOT_FOUND"),
    IN_VALIDATION(value = "IN_VALIDATION"),
    REGISTRATION(value = "IN_REGISTRATION"),
    CREATED(value = "CREATED"),
    CONCLUDED(value = "CONCLUDED");

    companion object {
        fun findByValue(value: String?) : UserStatus {
            return entries.find { it.value == value } ?: NOT_FOUND
        }
    }
}