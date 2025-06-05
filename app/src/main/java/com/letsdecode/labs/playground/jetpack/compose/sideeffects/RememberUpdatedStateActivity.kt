package com.letsdecode.labs.playground.jetpack.compose.sideeffects

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import com.letsdecode.labs.R
import kotlinx.coroutines.withContext

/*
• Splash Screen: Make a network call to decide the Next Activity
• Show Animation of 5 seconds, Network call is going on in parallel
• When the Animation ends
    • If network call succeeds, proceed with the received Next Activity
    • If network call fails or taking more time, proceed with the default Next Activity
 */

class RememberUpdatedStateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.remember_updated_state)) {
                    var welcomeMessage by remember {
                        mutableStateOf("Welcome Gaurav")
                    }
                    LaunchedEffect(Unit) {
                        val welcomeMessageFromNetwork = fetchWelcomeMessage()
                        welcomeMessage = welcomeMessageFromNetwork
                    }
                    RememberUpdatedStateExample(welcomeMessage = welcomeMessage)
                }
            }
        }
    }

}

@Composable
fun RememberUpdatedStateExample(welcomeMessage: String) {

    val context = LocalContext.current

    val updatedState by rememberUpdatedState(welcomeMessage)
//    val updatedState by remember { mutableStateOf(welcomeMessage) }

    LaunchedEffect(Unit) {
        // Animation for 5 seconds
        delay(5000L)
        // Show the welcome message
        Toast.makeText(context, updatedState, Toast.LENGTH_LONG).show()
    }

}

// simulating network call to fetch the welcome message
suspend fun fetchWelcomeMessage(): String {
    return withContext(Dispatchers.Default) {
        delay(3000L)
        return@withContext "Good Evening Gaurav"
    }
}