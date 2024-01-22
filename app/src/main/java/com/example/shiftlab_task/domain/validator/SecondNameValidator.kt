package com.example.shiftlab_task.domain.validator

import com.example.shiftlab_task.R

class SecondNameValidator: Validator {
    override fun validate(data: String, secondData: String): Int? {
        val pattern = Regex("[a-zA-Zа-яА-Я]+")

        return if (data.isEmpty()) {
            null
        } else if (!pattern.matches(data)) {
            R.string.second_name_error
        } else null
    }
}