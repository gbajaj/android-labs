package com.letsdecode.labs.playground.jetpack.compose.statehoisting

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import com.letsdecode.labs.R


class StateHoistingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.state_hoisting)) {
                    StateHoistingExample()
                }
            }
        }
    }

}

@Composable
fun StateHoistingExample() {
    var counter by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            StateHoistingButtonContent { counter++ }
            StateHoistingTextContent(counter)
        })
}

@Composable
fun StateHoistingButtonContent(onCounterChange: () -> Unit) {
    Button(onClick = { onCounterChange() }) {
        Text(text = stringResource(R.string.click_here))
    }
}

@Composable
fun StateHoistingTextContent(counter: Int) {
    Text(text = "$counter", modifier = Modifier.padding(20.dp))
}