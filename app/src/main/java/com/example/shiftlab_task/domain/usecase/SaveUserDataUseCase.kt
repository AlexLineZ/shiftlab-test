package com.example.shiftlab_task.domain.usecase

import com.example.shiftlab_task.data.model.UserModel
import com.example.shiftlab_task.domain.repository.UserRepository

class SaveUserDataUseCase (private val userRepository: UserRepository) {
    fun saveUser(user: UserModel){
        userRepository.saveUserData(user)
    }
}