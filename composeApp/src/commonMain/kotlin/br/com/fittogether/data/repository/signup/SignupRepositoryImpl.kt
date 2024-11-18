package br.com.fittogether.data.repository.signup

import br.com.fittogether.data.remote.api.signup.SignupAPI
import br.com.fittogether.data.remote.dto.mapper.signup.toDomain
import br.com.fittogether.data.remote.dto.request.VerifyEmailRequest
import br.com.fittogether.data.remote.dto.response.VerifyEmailResponse
import br.com.fittogether.data.remote.wrapper.ApiWrapper
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.signup.VerifyEmail
import br.com.fittogether.domain.repository.signup.SignupRepository

class SignupRepositoryImpl(
    private val signupAPI: SignupAPI,
    private val wrapper: ApiWrapper
) : SignupRepository {
    override suspend fun verifyEmail(request: VerifyEmailRequest) : ResultAPI<VerifyEmail?> {
        return wrapper(
            request = signupAPI.verifyEmail(request = request),
            mapper = VerifyEmailResponse::toDomain
        )
    }
}