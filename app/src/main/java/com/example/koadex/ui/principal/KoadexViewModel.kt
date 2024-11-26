
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.WeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KoadexViewModel(formsRepository: FormRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val koadexGeneralUiState: StateFlow<KoadexGeneralUiState> =
        formsRepository.getAllForms().map { KoadexGeneralUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexGeneralUiState()
            )

    val getAllUsers = formsRepository.getAllUsers()
    val getAllFormStates = formsRepository.getAllFormStates()

    val getWeatherById: (Int) -> Flow<WeatherEntity?> = { id ->
        formsRepository.getWeatherById(id)
    }
    val getSeasonById: (Int) -> Flow<SeasonEntity?> = { id ->
        formsRepository.getSeasonById(id)
    }
}

data class KoadexGeneralUiState(val koadexGeneralList: List<GeneralFormEntity> = listOf())