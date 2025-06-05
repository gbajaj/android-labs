package com.letsdecode.labs.playground.jetpack.compose.sideeffects

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.letsdecode.labs.ui.theme.LabsTheme
import kotlin.collections.toList
import kotlin.text.isNotEmpty
import com.letsdecode.labs.R

fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

class DerivedStateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LabsTheme() {
                com.letsdecode.labs.playground.jetpack.compose.ui.common.TopBarScaffold(stringResource(com.letsdecode.labs.R.string.derived_state)) {
                    DerivedStateScrollExample()
                }
            }
        }
    }

}

@Composable
fun DerivedStateScrollExample() {

    val listState = rememberLazyListState()

    val isEnabled by remember { derivedStateOf { listState.firstVisibleItemIndex > 20 } }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerivedStateEmailExample() {

    var email by remember { mutableStateOf("") }

    val isValidEmail by remember {
        derivedStateOf {
            return@derivedStateOf isValidEmail(email)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        TextField(
            modifier = Modifier.padding(8.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.enter_email)) }
        )

        Button(
            onClick = { },
            enabled = isValidEmail
        ) {
            Text(text = stringResource(R.string.submit))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerivedStateFormExample() {

    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    val submitEnabled by remember {
        derivedStateOf {
            return@derivedStateOf firstname.isNotEmpty() && lastname.isNotEmpty()
        }
    }

//    val submitEnabled by remember {
//        mutableStateOf(firstname.isNotEmpty() && lastname.isNotEmpty())
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            modifier = Modifier.padding(8.dp),
            value = firstname,
            onValueChange = { firstname = it },
            label = { Text(stringResource(R.string.enter_firstname)) }
        )

        TextField(
            modifier = Modifier.padding(8.dp),
            value = lastname,
            onValueChange = { lastname = it },
            label = { Text(stringResource(R.string.enter_lastname)) }
        )

        Button(
            onClick = { },
            enabled = submitEnabled // firstname.isNotEmpty() && lastname.isNotEmpty()
        ) {
            Text(text = stringResource(R.string.submit))
        }
    }

}