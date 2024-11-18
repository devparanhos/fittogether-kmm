package br.com.fittogether.domain.repository.signup

import br.com.fittogether.data.remote.dto.request.VerifyEmailRequest
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.signup.VerifyEmail

interface SignupRepository {
    suspend fun verifyEmail(request: VerifyEmailRequest) : ResultAPI<VerifyEmail?>
}