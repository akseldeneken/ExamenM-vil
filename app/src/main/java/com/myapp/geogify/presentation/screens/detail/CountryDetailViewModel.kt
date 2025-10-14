package com.myapp.geogify.presentation.screens.detail

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CountryDetailViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryDetailUiState())
    val uiState: StateFlow<CountryDetailUiState> = _uiState.asStateFlow()

    fun getCountry(code: String) {
        viewModelScope.launch {
            getCountryUseCase(code).collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> state.copy(
                            isLoading = true
                        )
                        is Result.Success -> state.copy(
                            country = result.data,
                            isLoading = false,
                            error = null
                        )
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