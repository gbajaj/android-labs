package com.letsdecode.labs.playground.jetpack.compose.sideeffects


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import com.letsdecode.labs.R

class SnapshotFlowActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.snapshot_flow)) {
                    SnapshotFlowScrollToTopExample()
                }
            }
        }
    }

}

@Composable
fun SnapshotFlowScrollToTopExample() {

    val listState = rememberLazyListState()

    var isEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex > 20 }
            .distinctUntilChanged()
            .collect {
                isEnabled = it
                Log.d("SnapshotFlowScrollToTopExample", "collect: $it")
            }
    }

    LazyColumn(state = listState) {
        items((1..100).toList()) {
            Text(
                "Item $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }

    Button(onClick = { }, enabled = isEnabled, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Scroll To Top")
    }

}

@Composable
fun SnapshotFlowExample() {

    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items((1..100).toList()) {
            Text(
                "Item $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > 0 }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                // logs when the user scrolls past the first item
                Log.d("SnapshotFlowExample", "collect: $it")
            }
    }

}