package br.com.fittogether.core.controller

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
}