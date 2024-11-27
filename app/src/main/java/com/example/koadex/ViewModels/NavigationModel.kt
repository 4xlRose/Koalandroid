package com.example.koadex.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.auth0.android.result.Credentials
import com.example.koadex.R
import com.example.koadex.data.FormRepository
import com.example.koadex.data.UserEntity
import com.example.koadex.navigate.getCurrentDate
import com.example.koadex.navigate.sampleUser
import com.example.koadex.ui.form.FormStateDetails
import kotlinx.coroutines.flow.Flow
import java.util.Date

class NavigationModel(private val formRepository: FormRepository): ViewModel() {
    var loggedUser by mutableStateOf(sampleUser)
    var accessToken by mutableStateOf("")
    var savedFormId by mutableIntStateOf(0)

    suspend fun insertUser(user: UserEntity) {
        formRepository.insertUser(user)
    }
    suspend fun updateUser(user: UserEntity) {
        formRepository.updateUser(user)
    }
    fun getUserByEmail(email: String): Flow<UserEntity?> {
        return formRepository.getUserByEmail(email)
    }
}