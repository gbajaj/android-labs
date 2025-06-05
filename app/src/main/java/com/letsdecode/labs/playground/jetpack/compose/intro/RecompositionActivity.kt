package com.letsdecode.labs.playground.jetpack.compose.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import com.letsdecode.labs.R

class RecompositionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.recomposition_example)) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        RecompositionExample()
                    }
                }
            }
        }
    }

}

@Composable
fun RecompositionExample() {
    var count by remember { mutableStateOf(0) }

    Button(
        onClick = { count++ },
        content = { Text("Count: $count") }
    )
}