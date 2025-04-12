package com.falmeida.tasky.feature.auth.domain.validator

import javax.inject.Inject

class AuthValidator @Inject constructor() : IAuthValidator {

    override fun isValidName(name: String): Boolean = name.length in 4..50

    override fun isValidEmail(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun isValidPassword(password: String): Boolean = password.length >= 9 &&
            password.any { it.isUpperCase() } &&
            password.any { it.isLowerCase() } &&
            password.any { it.isDigit() }
}