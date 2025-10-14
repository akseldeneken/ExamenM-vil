package com.myapp.geogify.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.geogify.data.local.preferences.UserPreferences
import com.myapp.geogify.domain.common.Result
import com.myapp.geogify.domain.usecase.GetCountryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountryListUseCase: GetCountryListUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // País con el que debería arrancar la app (si hay preferencia guardada)
    private val _startWithCountry = MutableStateFlow<String?>(null)
    val startWithCountry: StateFlow<String?> = _startWithCountry.asStateFlow()

    init {
        userPreferences.getLastCountry()?.let { code ->
            _startWithCountry.value = code
        }
        loadCountryList()
    }

    fun loadCountryList() {
        viewModelScope.launch {
            getCountryListUseCase().collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> state.copy(isLoading = true)
                        is Result.Success -> state.copy(
                            isLoading = false,
                            countryList = result.data,
                            error = null
                        )
                        is Result.Error -> state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    // Consumir el start country para que no vuelva a disparar navegación al regresar
    fun clearStartCountry() {
        _startWithCountry.value = null
    }

    fun clearLastCountryPreference() {
        userPreferences.clearLastCountry()
    }
}
