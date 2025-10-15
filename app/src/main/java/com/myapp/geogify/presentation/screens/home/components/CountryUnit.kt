package com.myapp.geogify.presentation.screens.home.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.myapp.geogify.domain.model.Country

// Cards para cada país en la lista
@Composable
fun CountryUnit(
    country: Country,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textStyle = MaterialTheme.typography.titleMedium
    val flagSize = with(LocalDensity.current) {
        // Approx: text size in dp + 4dp (slightly bigger than text)
        remember(textStyle.fontSize) {
            val textDp = textStyle.fontSize.toPx() / density
            (textDp + 4f).dp
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AsyncImage(
                model = country.flagUrl,
                contentDescription = "${country.name} flag",
                modifier = Modifier
                    .padding(2.dp)
                    .size(80.dp)
            )
            Text(
                text = country.name,
                style = textStyle,
                modifier = Modifier.weight(1f),
                maxLines = 1,
            )
        }
    }
}