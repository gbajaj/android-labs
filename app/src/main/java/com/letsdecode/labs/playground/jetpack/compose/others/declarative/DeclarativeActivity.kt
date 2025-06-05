
package com.letsdecode.labs.playground.jetpack.compose.others.declarative

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.letsdecode.labs.R

class DeclarativeActivity : AppCompatActivity() {

    private lateinit var declarativeViewModel: DeclarativeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        declarativeViewModel = DeclarativeViewModel()
        setContent {
            val displayText = declarativeViewModel.uiState.collectAsStateWithLifecycle()
            LabsTheme {
                TopBarScaffold(stringResource(R.string.declarative_example)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            SimpleText(displayText = displayText.value)
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

class DeclarativeViewModel : androidx.lifecycle.ViewModel() {

    private val _uiState = MutableStateFlow("")

    val uiState: StateFlow<String> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        // make an API call
        val data = "data from network"
        _uiState.value = data
    }

}