package me.tranduchuy.supabasejetpackcompose.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val image: String? = null
)
