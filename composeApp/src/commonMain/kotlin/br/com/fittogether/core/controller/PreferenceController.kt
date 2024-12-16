package br.com.fittogether.core.controller

import br.com.fittogether.core.util.toJson
import br.com.fittogether.core.util.toObj
import br.com.fittogether.domain.model.user.User
import com.russhwolf.settings.Settings

class PreferenceController(
    private val settings: Settings
) {
    companion object {
        const val BASE_URL = "https://fittogether-api.onrender.com"
        const val ONBOARDING_KEY = "ONBOARDING"
        const val EMAIL_REGISTRATION = "EMAIL_REGISTRATION"
        const val USER = "USER"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    fun hasOnboarding() : Boolean {
        return settings.getBoolean(ONBOARDING_KEY, false)
    }

    fun setOnboarding() {
        settings.putBoolean(ONBOARDING_KEY, true)
    }

    fun setEmailRegistration(email: String) {
        settings.putString(EMAIL_REGISTRATION, email)
    }

    fun getEmailRegistration() : String {
        return settings.getString(EMAIL_REGISTRATION, "")
    }

    fun setToken(token: String?) {
        settings.putString(ACCESS_TOKEN, token ?: "")
    }

    fun getToken() : String? {
        val token = settings.getString(ACCESS_TOKEN, "")
        return token.ifBlank { null }
    }

    fun getUser() : User? {
        return if (settings.getString(USER, defaultValue = "").isBlank()) {
            null
        } else {
            settings.getString(USER, "").toObj()
        }
    }

    fun setUser(user: User) {
        settings.putString(USER, user.toJson())
    }

    fun clearUser() {
        settings.remove(USER)
        settings.remove(ACCESS_TOKEN)
    }
}