package com.example.shiftlab_task.data.repository

import android.content.Context
import com.example.shiftlab_task.data.localstorage.LocalStorage
import com.example.shiftlab_task.data.model.UserModel
import com.example.shiftlab_task.domain.repository.UserRepository

class UserRepositoryImpl (context: Context): UserRepository {
    private val localStorage = LocalStorage(context)

    override fun saveUserData(user: UserModel) {
        localStorage.saveData(user)
    }

    override fun getUserData(): UserModel {
        return localStorage.getData()
    }

    override fun hasUserData(): Boolean {
        return localStorage.hasData()
    }
}