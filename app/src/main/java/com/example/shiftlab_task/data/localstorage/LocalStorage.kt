package com.example.shiftlab_task.data.localstorage

import android.content.Context
import com.example.shiftlab_task.data.model.UserModel

class LocalStorage (context: Context) : ILocalStorage {
    private val sharedPreferences =
        context.getSharedPreferences(ILocalStorage.KEY, Context.MODE_PRIVATE)

    override fun saveData(user: UserModel) {
        sharedPreferences.edit()
            .putString(ILocalStorage.FIRST_NAME_KEY, user.firstName)
            .putString(ILocalStorage.SECOND_NAME_KEY, user.secondName)
            .putString(ILocalStorage.BIRTHDAY_KEY, user.birthday)
            .putString(ILocalStorage.PASSWORD_KEY, user.password)
            .apply()
    }

    override fun getData(): UserModel {
        return UserModel(
            sharedPreferences.getString(ILocalStorage.FIRST_NAME_KEY, ILocalStorage.SPACE)!!,
            sharedPreferences.getString(ILocalStorage.SECOND_NAME_KEY, ILocalStorage.SPACE)!!,
            sharedPreferences.getString(ILocalStorage.BIRTHDAY_KEY, ILocalStorage.SPACE)!!,
            sharedPreferences.getString(ILocalStorage.PASSWORD_KEY, ILocalStorage.SPACE)!!,
        )
    }
    override fun hasData() : Boolean {
        return sharedPreferences.contains(ILocalStorage.FIRST_NAME_KEY)
    }
}