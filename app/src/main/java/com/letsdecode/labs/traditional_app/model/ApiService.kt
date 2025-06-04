package com.letsdecode.labs.traditional_app.model

import com.google.gson.Gson
import retrofit2.Response
import retrofit2.http.GET

object ApiService {
    // Define your Retrofit API service here
    // Example:
     @GET("products")
     suspend fun getProductsNetwork(): Response<ProductResponse> {
         // TODO: Implement Retrofit client and network request
         throw UnsupportedOperationException("API service not initialized.")
     }

     fun getProducts(): ProductResponse {
        val gson = Gson() // Example
        return gson.fromJson(ProductData.jsonString, ProductResponse::class.java)
     }
}