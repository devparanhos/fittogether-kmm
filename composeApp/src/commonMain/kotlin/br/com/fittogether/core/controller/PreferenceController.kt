package br.com.fittogether.core.controller

import com.russhwolf.settings.Settings

class PreferenceController(
    private val settings: Settings
) {
    companion object {
        const val BASE_URL = "https://fittogether-api.onrender.com"
        const val ONBOARDING_KEY = "ONBOARDING"
        const val EMAIL_REGISTRATION = "EMAIL_REGISTRATION"
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

    fun getUser() : String {
        return settings.getString("token", "")
    }

    fun setUser(token: String) {
        settings.putString("token", token)
    }
}