
package com.example.koadex.ui.principal

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KoadexViewModel(formsRepository: FormRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val koadexGeneralUiState: StateFlow<KoadexGeneralUiState> =
        formsRepository.getAllForms().map { KoadexGeneralUiState(it, formsRepository) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexGeneralUiState(formsRepository = formsRepository)
            )

    val getAllUsers = formsRepository.getAllUsers()

    val getUserById: (Int) -> Flow<UserEntity?> = { id ->
        formsRepository.getUserById(id)
    }
    val getAllFormStates = formsRepository.getAllFormStates()

    val getWeatherById: (Int) -> Flow<WeatherEntity?> = { id ->
        formsRepository.getWeatherById(id)
    }
    val getSeasonById: (Int) -> Flow<SeasonEntity?> = { id ->
        formsRepository.getSeasonById(id)
    }

//    val deleteForm = { form: GeneralFormEntity ->
//        formsRepository.deleteForm(form)
//    }
}

data class KoadexGeneralUiState(val koadexGeneralList: List<GeneralFormEntity> = listOf(), val formsRepository: FormRepository){

    suspend fun deleteForm(form: GeneralFormEntity){

        val stateFlow = formsRepository.getFormStateByFormId(form.id)
        stateFlow.collect{
            if (it != null) {
                formsRepository.deleteFormState(it)
            }
            formsRepository.deleteForm(form)
        }
    //formsRepository
    }

}