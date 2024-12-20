
package com.example.koadex.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koadex.data.FollowUpFormEntity
import com.example.koadex.data.FormRepository
import com.example.koadex.data.FormStateEntity
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.QuadrantFormEntity
import com.example.koadex.data.RouteFormEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.SpecieFormEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.data.WeatherFormEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class KoadexViewModel(formsRepository: FormRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val getAllForms: StateFlow<KoadexGeneralUiState> =
        formsRepository.getAllForms().map { KoadexGeneralUiState(it, formsRepository) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KoadexGeneralUiState(formsRepository = formsRepository)
            )

    val getSpecieFormById: (Int) -> Flow<SpecieFormEntity?> = { id ->
        formsRepository.getSpecieFormById(id)
    }

    val getFollowUpFormById: (Int) -> Flow<FollowUpFormEntity?> = { id ->
        formsRepository.getFollowUpFormById(id)
    }

    val getQuadrantFormById: (Int) -> Flow<QuadrantFormEntity?> = { id ->
        formsRepository.getQuadrantFormById(id)
    }

    val getRouteFormById: (Int) -> Flow<RouteFormEntity?> = { id ->
        formsRepository.getRouteFormById(id)
    }

    val getWeatherFormById: (Int) -> Flow<WeatherFormEntity?> = { id ->
        formsRepository.getWeatherFormById(id)
    }



    val getAllUsers = formsRepository.getAllUsers()

    val getUserById: (Int) -> Flow<UserEntity?> = { id ->
        formsRepository.getUserById(id)
    }

    val updateUser = { user: UserEntity ->
        viewModelScope.launch {
            formsRepository.updateUser(user)
        }
    }


    val getAllFormStates = formsRepository.getAllFormStates()

    val getWeatherById: (Int) -> Flow<WeatherEntity?> = { id ->
        formsRepository.getWeatherById(id)
    }
    val getSeasonById: (Int) -> Flow<SeasonEntity?> = { id ->
        formsRepository.getSeasonById(id)
    }


    val getFormStateByFormId: (Int) -> Flow<FormStateEntity?> = { formId ->
        formsRepository.getFormStateByFormId(formId)
    }

    val deleteForm = { form: GeneralFormEntity ->
        val stateFlow = getFormStateByFormId(form.id)
        viewModelScope.launch {
            stateFlow.collect {
                if (it != null) {
                    formsRepository.deleteFormState(it)
                }
                formsRepository.deleteForm(form)
            }
        }
    }

}

data class KoadexGeneralUiState(val koadexGeneralList: List<GeneralFormEntity> = listOf(), val formsRepository: FormRepository)