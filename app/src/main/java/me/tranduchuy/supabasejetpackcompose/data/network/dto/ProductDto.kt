package me.tranduchuy.supabasejetpackcompose.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(

    @SerialName("name")
    val name: String,

    @SerialName("price")
    val price: Double,

    @SerialName("image")
    val image: String? = null,

    @SerialName("id")
    val id: String = ""
)
