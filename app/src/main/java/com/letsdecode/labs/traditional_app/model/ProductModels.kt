package com.letsdecode.labs.traditional_app.model

data class ProductResponse(
    val id: String,
    val products: List<Product>,
    val totalProducts: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val status: Int,
    val kind: String,
    val etag: String
)

data class Product(
    val index: Int,
    val productId: String,
    val productName: String,
    val shortDescription: String,
    val longDescription: String,
    val price: String,
    val productImage: String,
    val reviewRating: Double,
    val reviewCount: Int,
    val inStock: Boolean
)