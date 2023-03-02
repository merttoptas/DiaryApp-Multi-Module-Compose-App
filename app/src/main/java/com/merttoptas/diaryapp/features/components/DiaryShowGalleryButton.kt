package com.merttoptas.diaryapp.features.components

import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by mertcantoptas on 02.03.2023
 */

@Composable
fun DiaryShowGalleryButton(
    galleryOpened: Boolean,
    galleryLoading: Boolean,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = if (galleryOpened)
                if (galleryLoading) "Loading" else "Hide Gallery"
            else "Show Gallery",
            style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiaryShowGalleryButtonPreview() {
    DiaryShowGalleryButton(galleryOpened = false, galleryLoading = false) {}
}