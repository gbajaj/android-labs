package com.letsdecode.labs.traditional_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsdecode.labs.traditional_app.model.ProductData
import com.letsdecode.labs.traditional_app.model.ProductRepository
import com.letsdecode.labs.traditional_app.model.ProductResponse
import com.letsdecode.labs.traditional_app.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Assume ProductRepository, ApiService, RetrofitClient are defined

// Using Kotlin's built-in Result<T> is also a good option as shown above.
class ProductViewModel(
    private val productRepository: ProductRepository // Inject repository
) : ViewModel() {
    private val _productsFlow = MutableStateFlow<Result<ProductResponse>>(
        Result.Success(
            ProductResponse(
                "",
                emptyList(), 0, 0, 0, 0, "", "",
            )
        ) // Initial empty state
    )
    val productsFlow: StateFlow<Result<ProductResponse>> = _productsFlow

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _productsFlow.value = Result.Loading // Indicate loading state for StateFlow
            try {
                val response = productRepository.getProductsFromLocalJson(ProductData.jsonString)
                _productsFlow.value = response
            } catch (e: Exception) {
                _productsFlow.value = Result.Error(e)
            }
        }
    }
}