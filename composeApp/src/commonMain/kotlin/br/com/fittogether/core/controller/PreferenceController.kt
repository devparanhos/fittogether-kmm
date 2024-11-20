package br.com.fittogether.core.controller

import br.com.fittogether.domain.model.signup.User
import com.russhwolf.settings.Settings

class PreferenceController(
    private val settings: Settings
) {
    companion object {
        const val BASE_URL = "https://fittogether-api.onrender.com"
        const val ONBOARDING_KEY = "ONBOARDING"
    }

    fun hasOnboarding() : Boolean {
        return settings.getBoolean(ONBOARDING_KEY, false)
    }

    fun setOnboarding() {
        settings.putBoolean(ONBOARDING_KEY, true)
    }

    fun getUser() : String {
        return settings.getString("token", "")
    }

    fun setUser(token: String) {
        settings.putString("token", token)
    }
}