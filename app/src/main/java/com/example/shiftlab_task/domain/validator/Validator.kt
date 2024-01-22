package com.example.shiftlab_task.domain.validator

import com.example.shiftlab_task.common.Constants

interface Validator {
    fun validate(data: String, secondData: String = Constants.EMPTY_STRING): Int?
}