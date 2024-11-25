
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormEntity
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import kotlinx.coroutines.flow.Flow
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

    val koadexGeneralUiState: StateFlow<KoadexGeneralUiState> =
        formsRespository.getFullDatabase().map { KoadexGeneralUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexGeneralUiState()
            )
}

data class KoadexUiState(val koadexList: List<FormEntity> = listOf()){

}

data class KoadexGeneralUiState(val koadexGeneralList: List<GeneralFormEntity> = listOf()){
}

interface FormRepository {
    fun getAllForms(): Flow<List<FormEntity>>
    fun getAllGeneralForms(): Flow<List<GeneralFormEntity>>
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

    // lo que estaba con FormEntity
    val koadexUiState: StateFlow<KoadexUiState> =
        formsRepository.getAllForms().map { KoadexUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexUiState()
            )

    // Lo mismo pero con GeneralFormEntity
    val koadexGeneralUiState: StateFlow<KoadexGeneralUiState> =
        formsRepository.getFullDatabase().map { KoadexGeneralUiState(it) }
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