package br.com.fittogether.data.remote.dto.mapper.signup

import br.com.fittogether.core.enums.Gender as GenderEnum
import br.com.fittogether.core.enums.RegistrationStep
import br.com.fittogether.core.enums.UserStatus
import br.com.fittogether.data.remote.dto.response.signup.CreateUserResponse
import br.com.fittogether.data.remote.dto.response.signup.ExercisesResponse
import br.com.fittogether.data.remote.dto.response.signup.GenderResponse
import br.com.fittogether.data.remote.dto.response.signup.GetGendersResponse
import br.com.fittogether.data.remote.dto.response.signup.GoalResponse
import br.com.fittogether.data.remote.dto.response.signup.PreferenceResponse
import br.com.fittogether.data.remote.dto.response.signup.ValidateCodeResponse
import br.com.fittogether.data.remote.dto.response.signup.VerifyEmailResponse
import br.com.fittogether.domain.model.exercise.Exercise
import br.com.fittogether.domain.model.gender.Gender
import br.com.fittogether.domain.model.goal.Goal
import br.com.fittogether.domain.model.preference.Preference
import br.com.fittogether.domain.model.signup.GetGender
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.model.signup.ValidateCode
import br.com.fittogether.domain.model.signup.VerifyEmail

fun VerifyEmailResponse.toDomain() : VerifyEmail {
    return VerifyEmail(
        status = UserStatus.findByValue(this.status),
        sendingCode = this.sendingCode,
        message = this.message
    )
}

fun ValidateCodeResponse.toDomain() : ValidateCode {
    return ValidateCode(
        registrationStep = this.registrationStep,
        userId = this.userId
    )
}

fun CreateUserResponse.toDomain() : User {
    return User(
        accessToken = this.accessToken,
        username = this.user?.username,
        id = this.user?.id,
        name = this.user?.name,
        registrationStep = RegistrationStep.findByValue(value = this.user?.registrationStep),
        gender = GenderEnum.findByValue(value = this.user?.gender),
        experience = this.user?.experience,
        goals = this.user?.goals?.map { it.toDomain() },
        exercises = this.user?.exercises?.map { it.toDomain() },
        preference = this.user?.preference?.toDomain()

    )
}

fun GoalResponse.toDomain() : Goal {
    return Goal(
        id = this.id,
        name = this.name,
        icon = this.icon
    )
}

fun ExercisesResponse.toDomain() : Exercise {
    return Exercise(
        id = this.id,
        name = this.name,
        icon = this.icon
    )
}

fun PreferenceResponse.toDomain() : Preference {
    return Preference(
        id = this.id,
        startAge = this.startAge,
        endAge = this.endAge,
        radiusDistance = this.radiusDistance,
        genders = this.genders?.map { it.toDomain() }
    )
}

fun GenderResponse.toDomain() : Gender {
    return Gender(
        id = this.id,
        name = this.name,
        icon = this.icon
    )
}

fun GetGendersResponse.toDomain() : GetGender {
    return GetGender(
        title = this.title,
        genders = this.genders?.map { it.toDomain() }
    )
}