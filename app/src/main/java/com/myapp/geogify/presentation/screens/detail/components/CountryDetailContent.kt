package com.myapp.geogify.presentation.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.text.NumberFormat
import java.util.Locale

@Suppress("ktlint:standard:function-naming")
@Composable
fun CountryDetailContent(
    name: String,
    flagUrl: String?,
    capital: String?,
    region: String?,
    subregion: String?,
    population: Long?,
    areaKm2: Double?,
    languages: List<String>,
    currencies: List<String>,
    modifier: Modifier = Modifier,
) {
    val scroll = rememberScrollState()
    val numberFormat = NumberFormat.getInstance(Locale.getDefault())

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = flagUrl,
            contentDescription = name,
            modifier = Modifier.size(200.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Key facts
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FactColumn(label = "Capital", value = capital)
            FactColumn(label = "Region", value = listOfNotNull(region, subregion).joinToString(" • ").ifEmpty { null })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FactColumn(
                label = "Population",
                value = population?.let { numberFormat.format(it) },
            )
            FactColumn(
                label = "Area",
                value = areaKm2?.let { "${numberFormat.format(it)} km²" },
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Languages
        SectionTitle("Languages")
        ChipRow(items = languages)

        Spacer(modifier = Modifier.height(16.dp))

        // Currencies
        SectionTitle("Currencies")
        ChipRow(items = currencies)
    }
}

@Composable
private fun FactColumn(label: String, value: String?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value ?: "—", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

@Composable
private fun ChipRow(items: List<String>, chipShape: Shape = MaterialTheme.shapes.small) {
    if (items.isEmpty()) {
        Text("—", style = MaterialTheme.typography.bodyMedium)
        return
    }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { item ->
            Chip(text = item, shape = chipShape)
        }
    }
}

@Composable
private fun Chip(text: String, shape: Shape) {
    Surface(
        shape = shape,
        tonalElevation = 1.dp,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}