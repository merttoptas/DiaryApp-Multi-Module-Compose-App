package com.merttoptas.diaryapp.features.components

import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.merttoptas.diaryapp.R

/**
 * Created by mertcantoptas on 20.02.2023
 */

@Composable
fun DiaryNavigateBack(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left_black),
            contentDescription = null
        )
    }
}