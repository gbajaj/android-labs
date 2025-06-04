package com.letsdecode.labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import com.letsdecode.labs.ui.theme.LabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Apply the padding globally to the whole BottomBar
                    Column(modifier = Modifier.padding(innerPadding)) {
                        // Use the padding inside the Column to apply it to the Text composable
                        FeatureFlow(
                            "Traditional UI",
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxWidth(),
                            onClick = { println("Column clicked!") })
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureFlow(
    name: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    onClick: () -> Unit = { -> }
) {
    Button(modifier = modifier, onClick = onClick) {
        Text(
            text = name,
            color = color,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabsTheme {
        FeatureFlow("Android")
    }
}