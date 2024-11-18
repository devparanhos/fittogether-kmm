package br.com.fittogether.presentation.feature.start.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fittogether.core.controller.PreferenceController

class StartViewModel(
    preferences: PreferenceController
) : ViewModel() {
    init {
        preferences.setOnboarding()
    }
}