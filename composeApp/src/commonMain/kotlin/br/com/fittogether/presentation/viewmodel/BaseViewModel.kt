package br.com.fittogether.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fittogether.data.remote.wrapper.ApiError
import br.com.fittogether.data.remote.wrapper.ResultAPI
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {
    suspend fun <D> callUseCase(
        prepareUi : (() -> Unit)? = null,
        useCase: suspend () -> ResultAPI<D?>,
        onSuccess: (D) -> Unit,
        onError: (ApiError?) -> Unit
    ) {
        try {
            prepareUi?.invoke()
            val request = useCase()
            request.data?.let {
                onSuccess(it)
            } ?: run {
                onError(
                    request.error
                )
            }
        } catch (exception: Exception) {
            onError(
                ApiError(
                    message = exception.message,
                    statusCode = 500,
                    errors = null,
                    internalCode = null
                )
            )
        }
    }

    protected fun <D> clearState(state: MutableStateFlow<D>, defaultValue: D) {
        state.value = defaultValue
    }
}