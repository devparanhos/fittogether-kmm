package br.com.fittogether.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fittogether.data.remote.wrapper.ApiErrorResponse
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.error.toDomain
import br.com.fittogether.domain.model.error.ApiError
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
                    request.error?.toDomain()
                )
            }
        } catch (exception: Exception) {
            onError(
                ApiErrorResponse(
                    message = exception.message,
                    statusCode = 500,
                    errors = null,
                    internalCode = null
                ).toDomain()
            )
        }
    }

    protected fun <D> clearState(state: MutableStateFlow<D>, defaultValue: D) {
        state.value = defaultValue
    }
}