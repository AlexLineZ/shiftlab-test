package com.example.shiftlab_task.domain.usecase

import com.example.shiftlab_task.common.Constants
import com.example.shiftlab_task.domain.validator.Validator

class DataValidateUseCase {
    fun invoke(
        validator: Validator,
        data: String,
        secondData: String = Constants.EMPTY_STRING
    ): Int? {
        return validator.validate(data, secondData)
    }
}