package com.merttoptas.diaryapp.features.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.*

/**
 * Created by mertcantoptas on 20.02.2023
 */

@Composable
fun DiaryAlertDialog(
    title: String,
    content: String?,
    onDismiss: () -> Unit,
    onYesClicked: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = content ?: "",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Normal
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onYesClicked()
                    onDismiss()
                })
            {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss)
            {
                Text(text = "No")
            }
        },
        onDismissRequest = onDismiss
    )
}
