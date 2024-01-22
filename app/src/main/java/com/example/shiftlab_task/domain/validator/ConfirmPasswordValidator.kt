package com.example.shiftlab_task.domain.validator

import com.example.shiftlab_task.R

class ConfirmPasswordValidator : Validator {
    override fun validate(data: String, secondData: String): Int? {
        return when {
            data != secondData -> R.string.password_result3
            else -> null
        }
    }
}