package com.letsdecode.labs.playground.jetpack.compose.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun UpdateConsumeCompositionLocal() {
    Column {
        UpdateProvideAndConsumeTextOne(displayText = "TextOne")
        UpdateProvideAndConsumeDisplayOtherTexts()
    }
}

@Composable
fun UpdateProvideAndConsumeDisplayOtherTexts() {
    UpdateProvideAndConsumeTextTwo(displayText = "TextTwo")
    val myColors = MyColors(
        primary = Color.Black,
        secondary = Color.Magenta
    )
    CompositionLocalProvider(LocalMyColors provides myColors) {
        UpdateProvideAndConsumeTextThree(displayText = "TextThree")
    }
}

@Composable
fun UpdateProvideAndConsumeTextOne(displayText: String) {
    Text(
        text = displayText,
        color = LocalMyColors.current.primary
    )
}

@Composable
fun UpdateProvideAndConsumeTextTwo(displayText: String) {
    Text(
        text = displayText,
        color = LocalMyColors.current.secondary
    )
}

@Composable
fun UpdateProvideAndConsumeTextThree(displayText: String) {
    Text(
        text = displayText,
        color = LocalMyColors.current.secondary
    )
}

