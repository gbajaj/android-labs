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
import com.letsdecode.labs.R
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme

class NeedOfStateHoistingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.need_of_state_hoisting)) {
                    NeedOfStateHoistingExample()
                }
            }
        }
    }

}

@Composable
fun NeedOfStateHoistingExample() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            NeedOfStateHoistingButtonContent()
            NeedOfStateHoistingTextContent()
        })
}

@Composable
fun NeedOfStateHoistingButtonContent() {
    var counter by remember { mutableStateOf(0) }
    Button(onClick = { counter++ }) {
        Text(text = stringResource(R.string.click_here))
    }
}

@Composable
fun NeedOfStateHoistingTextContent() {
    Text(text = "Show counter here", modifier = Modifier.padding(20.dp))
}