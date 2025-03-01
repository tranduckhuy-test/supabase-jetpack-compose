package me.tranduchuy.supabasejetpackcompose.presentation.feature.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.tranduchuy.supabasejetpackcompose.data.network.dto.ProductDto
import me.tranduchuy.supabasejetpackcompose.data.repository.ProductRepository
import me.tranduchuy.supabasejetpackcompose.domain.model.Product
import me.tranduchuy.supabasejetpackcompose.presentation.navigation.ProductDetailsDestination
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: Flow<Product?> = _product

    private val _name = MutableStateFlow("")
    val name: Flow<String> = _name

    private val _price = MutableStateFlow(0.0)
    val price: Flow<Double> = _price

    private val _imageUrl = MutableStateFlow("")
    val imageUrl: Flow<String> = _imageUrl

    init {
        val productId = savedStateHandle.get<String>(ProductDetailsDestination.PRODUCT_ID)
        productId?.let {
            getProduct(productId = it)
        }
    }

    private fun getProduct(productId: String) {
        viewModelScope.launch {
            val result = productRepository.getProduct(productId)

            if (result != null) {
                _product.value = result.asDomainModel()
                _name.value = result.name
                _price.value = result.price
                _imageUrl.value = result.image ?: ""
            }
        }
    }

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onPriceChange(price: Double) {
        _price.value = price
    }

    fun onSaveProduct(image: ByteArray) {
        viewModelScope.launch {
            productRepository.updateProduct(
                id = _product.value?.id ?: "",
                price = _price.value,
                name = _name.value,
                imageFile = image,
                imageName = "image_${_product.value?.id}",
            )
        }
    }

    fun onImageChange(url: String) {
        _imageUrl.value = url
    }

    private fun ProductDto.asDomainModel(): Product {
        return Product(
            id = this.id,
            name = this.name,
            price = this.price,
            image = this.image
        )
    }
}
