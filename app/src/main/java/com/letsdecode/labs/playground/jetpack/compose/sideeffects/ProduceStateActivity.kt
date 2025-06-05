package com.letsdecode.labs.playground.jetpack.compose.sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import com.letsdecode.labs.R

class ProduceStateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.produce_state)) {
                    ProduceStateExample("someUrl")
                }
            }
        }
    }

}

@Composable
fun ProduceStateExample(url: String) {

    val networkResult = loadDataFromNetwork(url)

    when (networkResult.value) {
        is NetworkResult.Success -> {
            ShowText((networkResult.value as NetworkResult.Success).data)
        }

        is NetworkResult.Loading -> {
            ShowText("Loading...")
        }

        is NetworkResult.Error -> {
            ShowText("Error")
        }
    }

}

@Composable
fun ShowText(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}

@Composable
fun loadDataFromNetwork(
    url: String, networkService: NetworkService = NetworkService()
): State<NetworkResult> {
    return produceState<NetworkResult>(
        initialValue = NetworkResult.Loading, url, networkService
    ) {

        val data = networkService.fetch(url)

        value = if (data.isEmpty()) {
            NetworkResult.Error
        } else {
            NetworkResult.Success(data)
        }
    }
}

sealed interface NetworkResult {

    data class Success(val data: String) : NetworkResult

    object Error : NetworkResult

    object Loading : NetworkResult

}

class NetworkService {

    suspend fun fetch(url: String): String {
        return withContext(Dispatchers.IO) {
            delay(3000L)
            return@withContext "Data from Network"
        }
    }

}
