package com.example.shiftlab_task.domain.usecase

import com.example.shiftlab_task.domain.repository.UserRepository

class GetUserDataUseCase(private val userRepository: UserRepository){
    fun getUserName(): String{
        return userRepository.getUserData().firstName
    }
}