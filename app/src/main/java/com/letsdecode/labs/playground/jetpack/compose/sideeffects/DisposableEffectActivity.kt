package com.letsdecode.labs.playground.jetpack.compose.sideeffects

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold
import com.letsdecode.labs.ui.theme.LabsTheme
import com.letsdecode.labs.R

class DisposableEffectActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                TopBarScaffold(stringResource(R.string.disposable_effect)) {
                    TestingTextField()
                    DisposableEffectExample()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestingTextField() {

    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = text,
            onValueChange = { text = it }
        )
    }
}

@Composable
fun DisposableEffectExample() {

    val view = LocalView.current

    DisposableEffect(key1 = Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyboardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())
            Log.d("DisposableEffectExample", isKeyboardVisible.toString())
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

}