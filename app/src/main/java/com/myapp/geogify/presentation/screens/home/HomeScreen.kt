package com.myapp.geogify.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myapp.geogify.presentation.screens.home.components.CountryListContent
import com.myapp.geogify.presentation.screens.home.components.SearchTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCountryClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Countries", "Search")

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val startCode by viewModel.startWithCountry.collectAsStateWithLifecycle()

    LaunchedEffect(startCode) {
        startCode?.let { code ->
            onCountryClick(code)
            viewModel.clearStartCountry()
        }
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Geogify") }) },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        label = { Text(title) },
                        icon = { }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            when (selectedTabIndex) {
                0 -> CountryListContent(
                    countryList = uiState.countryList,
                    isLoading = uiState.isLoading,
                    error = uiState.error,
                    onCountryClick = onCountryClick,
                    onRetry = { viewModel.loadCountryList() },
                )
                1 -> SearchTab(onCountryClick = onCountryClick)
            }
        }
    }
}
