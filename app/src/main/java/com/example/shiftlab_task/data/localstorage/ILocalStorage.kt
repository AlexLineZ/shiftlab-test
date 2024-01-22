package com.example.shiftlab_task.data.localstorage

import com.example.shiftlab_task.data.model.UserModel

interface ILocalStorage {
    companion object {
        const val FIRST_NAME_KEY = "userFirstName"
        const val SECOND_NAME_KEY = "userSecondName"
        const val BIRTHDAY_KEY = "userDateOfBirthday"
        const val PASSWORD_KEY = "userPassword"
        const val SPACE = " "
        const val KEY = "save"
    }

    fun saveData(user: UserModel)

    fun getData(): UserModel

    fun hasData(): Boolean
}