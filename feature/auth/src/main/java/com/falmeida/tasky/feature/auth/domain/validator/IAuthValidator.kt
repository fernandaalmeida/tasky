package com.falmeida.tasky.feature.auth.domain.validator

interface IAuthValidator {
    fun isValidName(name: String): Boolean
    fun isValidEmail(email: String): Boolean
    fun isValidPassword(password: String): Boolean
}