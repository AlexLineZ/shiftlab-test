package com.example.shiftlab_task.domain.usecase

import com.example.shiftlab_task.domain.repository.UserRepository

class HasUserDataUseCase (private val userRepository: UserRepository) {
    fun hasUserData() : Boolean {
        return userRepository.hasUserData()
    }
}