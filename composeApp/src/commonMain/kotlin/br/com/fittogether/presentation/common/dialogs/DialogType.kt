package br.com.fittogether.presentation.common.dialogs


sealed class DialogType() {
    data object Email : DialogType()
}