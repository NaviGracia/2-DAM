package com.example.jetpacktest

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = { Icon(Icons.Default.Info, contentDescription = "") },
        title =
        {
            Text( modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                text = "Título",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text( text = "Cuerpo del mensaje")
        },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
            Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            )
            {
                Text("Dismiss")
            }
        }
    )
}