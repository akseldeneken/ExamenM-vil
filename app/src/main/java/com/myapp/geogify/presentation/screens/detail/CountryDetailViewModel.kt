package com.myapp.geogify.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.geogify.data.local.preferences.UserPreferences
import com.myapp.geogify.domain.common.Result
import com.myapp.geogify.domain.usecase.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryDetailUiState())
    val uiState: StateFlow<CountryDetailUiState> = _uiState.asStateFlow()

    fun getCountry(code: String) {
        viewModelScope.launch {
            getCountryUseCase(code).collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> state.copy(isLoading = true)
                        is Result.Success -> {
                            userPreferences.saveLastCountry(code)
                            state.copy(
                                country = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                        is Result.Error -> state.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}
