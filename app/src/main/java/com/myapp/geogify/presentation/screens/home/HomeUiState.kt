package com.myapp.geogify.presentation.screens.home

import com.myapp.geogify.domain.model.Country

data class HomeUiState(
    val countryList: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)