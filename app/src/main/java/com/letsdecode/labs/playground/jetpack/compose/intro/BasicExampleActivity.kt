package com.letsdecode.labs.playground.jetpack.compose.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import com.letsdecode.labs.R

class BasicExampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.basic_example)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            SimpleText("Simple Text")
                            SimpleButton()
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun SimpleText(displayText: String) {
    Text(
        text = displayText,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 20.sp,
            color = Color.Black
        )
    )
}

@Composable
fun SimpleButton() {
    Button(
        modifier = Modifier.padding(16.dp),
        onClick = {}) {
        Text(
            text = "Simple Button", style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )
    }
}

@Preview
@Composable
fun SimpleTextPreview() {
    SimpleText("Simple Text")
}

