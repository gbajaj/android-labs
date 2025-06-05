package com.letsdecode.labs.playground.jetpack.compose.sideeffects

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import com.letsdecode.labs.R

class LaunchedEffectActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.launched_effect)) {
                    LaunchedEffectExample()
                }
            }
        }
    }

}

@Composable
fun LaunchedEffectExample() {

    var counter by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val toastText = stringResource(R.string.launched_effect)

    LaunchedEffect(key1 = true) {
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { counter++ }) {
            Text(text = stringResource(R.string.click_here))
        }
        Text(text = "$counter", modifier = Modifier.padding(20.dp))
    }

}