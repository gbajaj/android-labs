package com.letsdecode.labs.playground.jetpack.compose.compositionlocal

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AccessContext() {

    val context = LocalContext.current

}