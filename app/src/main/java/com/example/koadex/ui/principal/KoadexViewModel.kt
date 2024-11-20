
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KoadexViewModel(formsRespository: FormRepository) : ViewModel() {

    val koadexUiState: StateFlow<KoadexUiState> =
        formsRespository.getFullDatabase().map { KoadexUiState(formsRespository, it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class KoadexUiState(val formsRespository: FormRepository? = null, val koadexList: List<GeneralFormEntity>? = null)
{
    fun getFormById(id: Int) = formsRespository?.getFormById(id)

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}