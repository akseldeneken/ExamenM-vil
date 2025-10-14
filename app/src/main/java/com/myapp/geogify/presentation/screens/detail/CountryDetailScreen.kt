package com.myapp.geogify.presentation.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myapp.geogify.presentation.screens.detail.components.CountryDetailContent

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    code: String,
    onRetry: () -> Unit = {},
    onBackClick: () -> Unit,
    viewModel: CountryDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Solo pedimos el país; el ViewModel se encarga de guardarlo cuando llega el Success
    LaunchedEffect(code) { viewModel.getCountry(code) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Country") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { viewModel.getCountry(code) }) {
                            Text("Retry")
                        }
                    }
                }
                uiState.country != null -> {
                    val c = uiState.country!!
                    CountryDetailContent(
                        name = c.name,
                        flagUrl = c.flagUrl,
                        capital = c.capital,
                        region = c.region,
                        languages = c.languages
                    )
                }
            }
        }
    }
}
