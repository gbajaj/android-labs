package com.letsdecode.labs.traditional_app.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.letsdecode.labs.R
import com.letsdecode.labs.traditional_app.model.ProductRepository
import com.letsdecode.labs.traditional_app.model.Result
import com.letsdecode.labs.traditional_app.viewmodel.ProductViewModel
import com.letsdecode.labs.traditional_app.viewmodel.ProductViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TraditionalUI : AppCompatActivity() {

    private val TAG = "TraditionalUI"
    private val viewModel: ProductViewModel by viewModels {
        val productRepository = ProductRepository(Dispatchers.IO)
        ProductViewModelFactory(productRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_traditional_ui)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productsFlow.collect {
                    when (it) {
                        is Result.Success -> {
                            println("$TAG: Total Products: ${it.data.products}")
                        }
                        is Result.Error -> println("$TAG: Error - ${it.exception.message}")
                        is Result.Loading -> println("$TAG: Loading")
                    }
                }
            }
        }
    }
}
