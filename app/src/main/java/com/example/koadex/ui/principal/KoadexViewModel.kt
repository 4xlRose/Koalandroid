
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormEntity
import com.example.koadex.data.FormRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KoadexViewModel(formsRespository: FormRepository) : ViewModel() {

    val koadexUiState: StateFlow<KoadexUiState> =
        formsRespository.getAllForms().map { KoadexUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class KoadexUiState(val koadexList: List<FormEntity> = listOf()){

}

/*
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormEntity
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.FormRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.Flow

class KoadexViewModel(private val formsRepository: FormRepository) : ViewModel() {

    // Original state for FormEntity
    val koadexUiState: StateFlow<KoadexUiState> =
        formsRepository.getAllForms().map { KoadexUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexUiState()
            )

    // New state for GeneralFormEntity
    val koadexGeneralUiState: StateFlow<KoadexGeneralUiState> =
        formsRepository.getAllGeneralForms().map { KoadexGeneralUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexGeneralUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

// Original UI state for FormEntity
data class KoadexUiState(val koadexList: List<FormEntity> = listOf())

// New UI state for GeneralFormEntity
data class KoadexGeneralUiState(val koadexGeneralList: List<GeneralFormEntity> = listOf())

// Extension of FormRepository to add GeneralForm support
interface FormRepository {
    fun getAllForms(): Flow<List<FormEntity>>
    fun getAllGeneralForms(): Flow<List<GeneralFormEntity>>
}
*/