package com.letsdecode.labs.playground.jetpack.compose.intro

import androidx.compose.runtime.Composable

@Composable
fun UsingAtFirstPlace(showError: Boolean) {
    LoginScreen(showError)
}

@Composable
fun UsingAtSecondPlace(showError: Boolean) {
    LoginScreen(showError)
}

@Composable
fun LoginScreen(showError: Boolean) {
    if (showError) {
        LoginError()
    }
    LoginInput()
}

@Composable
fun LoginInput() {

}

@Composable
fun LoginError() {

}