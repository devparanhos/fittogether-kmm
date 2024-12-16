package br.com.fittogether.data.repository.signup

import br.com.fittogether.data.remote.api.signup.SignupAPI
import br.com.fittogether.data.remote.dto.mapper.signup.toDomain
import br.com.fittogether.data.remote.dto.request.signup.CreateUserRequest
import br.com.fittogether.data.remote.dto.request.signup.SetGenderRequest
import br.com.fittogether.data.remote.dto.request.signup.ValidateCodeRequest
import br.com.fittogether.data.remote.dto.request.signup.VerifyEmailRequest
import br.com.fittogether.data.remote.dto.response.signup.CreateUserResponse
import br.com.fittogether.data.remote.dto.response.signup.GetGendersResponse
import br.com.fittogether.data.remote.dto.response.signup.GetGoalsResponse
import br.com.fittogether.data.remote.dto.response.signup.ValidateCodeResponse
import br.com.fittogether.data.remote.dto.response.signup.VerifyEmailResponse
import br.com.fittogether.data.remote.wrapper.ApiWrapper
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.signup.GetGender
import br.com.fittogether.domain.model.signup.GetGoal
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.model.signup.ValidateCode
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

    override suspend fun validateCode(request: ValidateCodeRequest): ResultAPI<ValidateCode?> {
        return wrapper(
            request = signupAPI.validateCode(request = request),
            mapper = ValidateCodeResponse::toDomain
        )
    }

    override suspend fun createUser(request: CreateUserRequest): ResultAPI<User?> {
        return wrapper(
            request = signupAPI.createUser(request = request),
            mapper = CreateUserResponse::toDomain
        )
    }

    override suspend fun getGenders(): ResultAPI<GetGender?> {
        return wrapper(
            request = signupAPI.getGenders(),
            mapper = GetGendersResponse::toDomain
        )
    }

    override suspend fun setGender(userId: Int?, request: SetGenderRequest): ResultAPI<User?> {
        return wrapper(
            request = signupAPI.setGender(userId = userId, request = request),
            mapper = CreateUserResponse::toDomain
        )
    }

    override suspend fun getGoals(): ResultAPI<GetGoal?> {
        return wrapper(
            request = signupAPI.getGoals(),
            mapper = GetGoalsResponse::toDomain
        )
    }
}