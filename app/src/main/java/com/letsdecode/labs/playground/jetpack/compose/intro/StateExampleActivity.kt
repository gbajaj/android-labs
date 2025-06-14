package com.letsdecode.labs.playground.jetpack.compose.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import com.letsdecode.labs.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateExampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.state_example)) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            TextFieldWithoutState()
                            TextFieldWithNormalVariable()
                            TextFieldWithState()
                        })
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithoutState() {
    TextField(modifier = Modifier.padding(8.dp),
        value = "",
        onValueChange = { },
        label = { Text(stringResource(R.string.enter_firstname)) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithNormalVariable() {
    var lastname = ""
    TextField(modifier = Modifier.padding(8.dp),
        value = lastname,
        onValueChange = { lastname = it },
        label = { Text(stringResource(R.string.enter_lastname)) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithState() {
    var username by remember { mutableStateOf("") }
    TextField(modifier = Modifier.padding(8.dp),
        value = username,
        onValueChange = { username = it },
        label = { Text(stringResource(R.string.enter_username)) })
}

// Stateful
@Composable
fun StatefulCounter() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text("Clicked $count times")
    }
}

// Stateless
@Composable
fun StatelessCounter(count: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Clicked $count times")
    }
}

@Composable
fun StatefulUI(viewModel: NewsViewModel) {
    val news = viewModel.uiState.collectAsStateWithLifecycle()
    Text(news.value)
    Button(onClick = {
        viewModel.fetchNews()
    }) {
        Text("Click Me")
    }
}

@Composable
fun StatelessUI(data: String, onClick: () -> Unit) {
    Text(data)
    Button(onClick = {
        onClick()
    }) {
        Text("Click Me")
    }
}

@Composable
fun NewsScreenUsingStatefulUI(viewModel: NewsViewModel = viewModel()) {
    StatefulUI(viewModel = viewModel)
}

@Composable
fun LanguageScreenUsingStatefulUI(viewModel: LanguageViewModel = viewModel()) {
//    StatefulUI(viewModel = viewModel)
}

@Composable
fun NewsScreenUsingStatelessUI(viewModel: NewsViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StatelessUI(data = uiState) {
        viewModel.fetchNews()
    }
}

@Composable
fun LanguageScreenUsingStatelessUI(viewModel: LanguageViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StatelessUI(data = uiState) {
        viewModel.fetchLanguages()
    }
}

class NewsViewModel : androidx.lifecycle.ViewModel() {

    private val _uiState = MutableStateFlow("")

    val uiState: StateFlow<String> = _uiState

    fun fetchNews() {

    }

}

class LanguageViewModel : androidx.lifecycle.ViewModel() {

    private val _uiState = MutableStateFlow("")

    val uiState: StateFlow<String> = _uiState

    fun fetchLanguages() {

    }

}





