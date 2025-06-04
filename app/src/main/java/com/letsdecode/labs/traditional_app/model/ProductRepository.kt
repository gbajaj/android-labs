package com.letsdecode.labs.traditional_app.model

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

// Assume ApiService and ProductResponse are defined as above
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>() // Optional for loading states
}
class ProductRepository(private val dispatcher: CoroutineDispatcher, val apiService: ApiService = ApiService, ) {

    /**
     * Fetches products from the network and emits them as a Flow.
     * This approach is good for observing data changes or handling streams.
     */
    fun getProductsStreamFlow(): Flow<Result<ProductResponse>> = flow {
        try {
            val productResponse = getProductsFromLocalJson(ProductData.jsonString)

            emit(productResponse)
        } catch (e: Exception) {
            // Handle other exceptions like network connectivity issues
            emit(Result.Error(e))
        }
    }.flowOn(dispatcher) // Perform network operations on the IO dispatcher

    /**
     * Fetches products using a simple suspend function.
     * This is suitable for one-shot requests.
     * Returns ProductResponse directly or throws an exception on failure.
     */
    suspend fun getProductsOnce(): ProductResponse {
        try {
            //TODO: Apis service not initialized
            val response = apiService.getProductsNetwork()
            if (response.isSuccessful) {
                return response.body() ?: throw IOException("Empty response body")
            } else {
                throw IOException("API Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            // You might want to re-throw a custom domain exception here
            throw e // Or handle it more gracefully depending on your app's needs
        }
    }

    /**
     * Example of fetching and parsing local JSON data (like in your TraditionalUI example).
     * This doesn't require Retrofit but uses Gson directly.
     */
    fun getProductsFromLocalJson(jsonString: String): Result<ProductResponse> {
        // You'd need to have Gson instance available here
        // For simplicity, assuming you have a way to get Gson
        // In a real app, you might inject Gson or have a utility for it.
         val gson = Gson() // Example
         return try {
             val productResponse = apiService.getProducts()
             Result.Success(productResponse)
         } catch (e: Exception) {
             Result.Error(e)
         }
        // For now, let's assume this is handled elsewhere or you'd integrate Gson properly.
        // This method is more for illustrating the "parsing" part if data isn't from network.
        // The function `parseProductJson` from your TraditionalUI.kt is a good example.
        // You could move that logic into the repository if it makes sense for your architecture.
        throw UnsupportedOperationException("Local JSON parsing needs Gson instance setup.")
    }
}
