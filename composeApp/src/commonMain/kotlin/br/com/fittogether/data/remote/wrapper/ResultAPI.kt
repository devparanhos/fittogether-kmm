package br.com.fittogether.data.remote.wrapper

data class ResultAPI<D>(
    val data: D?,
    val error: ApiError? = null
)