package br.com.fittogether.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fittogether.data.remote.wrapper.ApiError
import br.com.fittogether.data.remote.wrapper.ResultAPI

open class BaseViewModel : ViewModel() {
    suspend fun <D> callUseCase(
        prepareUi : (() -> Unit)? = null,
        useCase: suspend () -> ResultAPI<D?>,
        onSuccess: (D) -> Unit,
        onError: (ApiError) -> Unit
    ) {
        try {
            prepareUi?.invoke()
            val request = useCase()
            request.data?.let {
                onSuccess(it)
            } ?: run {
                onError(
                    request.error ?: ApiError(
                        message = "Aconteceu algo de errado. Tente novamente!",
                        statusCode = 500,
                        errors = null,
                        internalCode = ""
                    )
                )
            }
        } catch (exception: Exception) {
            onError(
                ApiError(
                    message = "Aconteceu algo de errado. Tente novamente!",
                    statusCode = 500,
                    errors = null,
                    internalCode = ""
                )
            )
        }
    }
}