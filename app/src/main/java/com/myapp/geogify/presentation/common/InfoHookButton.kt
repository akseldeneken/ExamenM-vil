package com.myapp.geogify.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*

// Botón del AppBar que abre un diálogo informativo
@Composable
fun InfoHookButton() {
    var open by remember { mutableStateOf(false) }

    IconButton(onClick = { open = true }) {
        Icon(imageVector = Icons.Default.Info, contentDescription = "About")
    }

    if (open) {
        AlertDialog(
            onDismissRequest = { open = false },
            confirmButton = {
                TextButton(onClick = { open = false }) { Text("OK") }
            },
            title = { Text("Cómo está hecha la app") },
            text = { Text(HookInfo.SELF_EXPLAINED_TEXT) }
        )
    }
}
