package com.example.shiftlab_task.domain.repository

import com.example.shiftlab_task.data.model.UserModel

interface UserRepository {
    fun saveUserData(user: UserModel)
    fun getUserData(): UserModel
    fun hasUserData(): Boolean
}