
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KoadexViewModel(formsRespository: FormRepository) : ViewModel() {


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val koadexGeneralUiState: StateFlow<KoadexGeneralUiState> =
        formsRespository.getAllForms().map { KoadexGeneralUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexGeneralUiState()
            )
}

data class KoadexUiState(val koadexList: List<GeneralFormEntity> = listOf()){

}

data class KoadexGeneralUiState(val koadexGeneralList: List<GeneralFormEntity> = listOf()){
}

interface FormRepository {
    fun getAllForms(): Flow<List<GeneralFormEntity>>
    fun getAllGeneralForms(): Flow<List<GeneralFormEntity>>
}