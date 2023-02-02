package com.merttoptas.diaryapp.features.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by mertcantoptas on 02.02.2023
 */

@Composable
fun DiaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.primary,
        disabledContentColor = Color.Gray,
        disabledBackgroundColor = Color.Gray,
    ),
    textColor: Color = Color.Black,
    textStyle: TextStyle = MaterialTheme.typography.button,
    border: BorderStroke? = null,
    shape: Shape = MaterialTheme.shapes.small,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(50.dp),
        enabled = enabled,
        colors = colors,
        shape = shape,
        border = border,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        content = {
            Text(
                text = text,
                style = textStyle,
                color = textColor
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    DiaryButton(
        enabled = true,
        modifier = Modifier,
        onClick = {},
        text = "Diary Button",
        textColor = Color.White,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.primary
        )
    )
}