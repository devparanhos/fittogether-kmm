package br.com.fittogether.domain.repository.signup

import br.com.fittogether.data.remote.dto.request.signup.CreateUserRequest
import br.com.fittogether.data.remote.dto.request.signup.ValidateCodeRequest
import br.com.fittogether.data.remote.dto.request.signup.VerifyEmailRequest
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.model.signup.ValidateCode
import br.com.fittogether.domain.model.signup.VerifyEmail

interface SignupRepository {
    suspend fun verifyEmail(request: VerifyEmailRequest) : ResultAPI<VerifyEmail?>
    suspend fun validateCode(request: ValidateCodeRequest) : ResultAPI<ValidateCode?>
    suspend fun createUser(request: CreateUserRequest) : ResultAPI<User?>
}