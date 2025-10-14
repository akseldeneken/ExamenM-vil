package com.myapp.geogify.presentation.screens.detail

import com.myapp.geogify.domain.model.Country

data class CountryDetailUiState(
    val country: Country? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)