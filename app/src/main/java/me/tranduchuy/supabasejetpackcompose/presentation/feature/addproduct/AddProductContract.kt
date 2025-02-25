package me.tranduchuy.supabasejetpackcompose.presentation.feature.addproduct

import kotlinx.coroutines.flow.Flow
import me.tranduchuy.supabasejetpackcompose.domain.usecase.CreateProductUseCase

interface AddProductContract {

    val navigateAddProductSuccess: Flow<CreateProductUseCase.Output?>
    val isLoading: Flow<Boolean>
    val showSuccessMessage: Flow<Boolean>
    fun onCreateProduct(name: String, price: Double)
    fun onAddMoreProductSelected()
    fun onRetrySelected()
}