package com.merttoptas.diaryapp.features.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.merttoptas.diaryapp.ui.theme.DiaryAppTheme

/**
 * Created by mertcantoptas on 20.02.2023
 */

@Composable
fun DiaryTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
    isCenterTitle: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    title: @Composable () -> Unit = {
        Text(
            text = text,
            textAlign = if (isCenterTitle) TextAlign.Center else TextAlign.Start,
            modifier = if (isCenterTitle) Modifier.fillMaxWidth() else Modifier,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    },
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )
}

@Preview
@Composable
private fun PreviewTopAppBar() {
    DiaryAppTheme() {
        DiaryTopAppBar(
            text = "App Bar Title",
            isCenterTitle = false,
            navigationIcon = {
                DiaryNavigateBack({})
            }
        )
    }
}