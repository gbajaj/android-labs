package com.letsdecode.labs.traditional_app.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.letsdecode.labs.R
import com.letsdecode.labs.databinding.ActivityTraditionalUiBinding
import com.letsdecode.labs.traditional_app.model.Product
import com.letsdecode.labs.traditional_app.model.ProductRepository
import com.letsdecode.labs.traditional_app.model.Result
import com.letsdecode.labs.traditional_app.view.adapter.ProductAdapter
import com.letsdecode.labs.traditional_app.viewmodel.ProductViewModel
import com.letsdecode.labs.traditional_app.viewmodel.ProductViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TraditionalUI : AppCompatActivity() {

    private val TAG = "TraditionalUI"
    private lateinit var binding: ActivityTraditionalUiBinding
    private val viewModel: ProductViewModel by viewModels {
        val productRepository = ProductRepository(Dispatchers.IO)
        ProductViewModelFactory(productRepository)
    }
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraditionalUiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set the title in the action bar
        supportActionBar?.title = "Products"
        
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupRecyclerView()
        
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productsFlow.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            productAdapter.submitList(result.data.products)
                            binding.productRecyclerView.visibility = View.VISIBLE
                            // Hide loading indicator if you have one
                        }
                        is Result.Error -> {
                            // Handle error state (e.g., show error message)
                            println("$TAG: Error - ${result.exception.message}")
                        }
                        is Result.Loading -> {
                            // Show loading indicator if you have one
                            println("$TAG: Loading")
                        }
                    }
                }
            }
        }
    }
    
    private fun setupRecyclerView() {
        binding.productRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TraditionalUI)
            adapter = productAdapter
            setHasFixedSize(true)
        }
    }
}
