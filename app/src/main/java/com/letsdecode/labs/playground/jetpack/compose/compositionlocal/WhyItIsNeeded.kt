package com.letsdecode.labs.playground.jetpack.compose.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

data class Colors(val primary: Color = Color.Blue, val secondary: Color = Color.Gray)

@Preview
@Composable
fun WhyItIsNeeded() {
    val colors = Colors()
    Column {
        TextOne(displayText = "TextOne", colors = colors)
        DisplayOtherTexts(colors)
    }
}

@Composable
fun DisplayOtherTexts(colors: Colors) {
    TextTwo(displayText = "TextTwo", colors = colors)
    TextThree(displayText = "TextThree", colors = colors)
}

@Composable
fun TextOne(displayText: String, colors: Colors) {
    Text(
        text = displayText,
        color = colors.primary
    )
}

@Composable
fun TextTwo(displayText: String, colors: Colors) {
    Text(
        text = displayText,
        color = colors.secondary
    )
}

@Composable
fun TextThree(displayText: String, colors: Colors) {
    Text(
        text = displayText,
        color = colors.secondary
    )
}