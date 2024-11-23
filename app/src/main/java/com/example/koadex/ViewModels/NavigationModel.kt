package com.example.koadex.ViewModels

import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.UserEntity
import kotlinx.coroutines.flow.Flow

class NavigationModel(private val formRepository: FormRepository): ViewModel() {
    suspend fun insertUser(user: UserEntity) {
        formRepository.insertUser(user)
    }
    fun getUserByEmail(email: String): Flow<UserEntity?> {
        return formRepository.getUserByEmail(email)
    }
}