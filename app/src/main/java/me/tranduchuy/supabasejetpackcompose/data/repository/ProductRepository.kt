package me.tranduchuy.supabasejetpackcompose.data.repository

import me.tranduchuy.supabasejetpackcompose.data.network.dto.ProductDto
import me.tranduchuy.supabasejetpackcompose.domain.model.Product

interface ProductRepository {
    suspend fun createProduct(product: Product): Boolean
    suspend fun getProducts(): List<ProductDto>?
    suspend fun getProduct(id: String): ProductDto?
    suspend fun deleteProduct(id: String)
    suspend fun updateProduct(
        id: String, name: String, price: Double, imageName: String, imageFile: ByteArray
    )
}