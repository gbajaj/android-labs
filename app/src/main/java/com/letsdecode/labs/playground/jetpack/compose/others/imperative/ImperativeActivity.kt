package com.letsdecode.labs.playground.jetpack.compose.others.imperative

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImperativeActivity : AppCompatActivity() {

    private lateinit var imperativeViewModel: ImperativeViewModel
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = TextView(this)
        setContentView(textView)

        imperativeViewModel = ImperativeViewModel()

        lifecycleScope.launch {
            imperativeViewModel.uiState.collect {
                textView.text = it
            }
        }
    }

}

class ImperativeViewModel : androidx.lifecycle.ViewModel() {

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