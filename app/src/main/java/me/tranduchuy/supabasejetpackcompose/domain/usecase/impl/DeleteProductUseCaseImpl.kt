package me.tranduchuy.supabasejetpackcompose.domain.usecase.impl

import me.tranduchuy.supabasejetpackcompose.data.repository.ProductRepository
import me.tranduchuy.supabasejetpackcompose.domain.usecase.DeleteProductUseCase
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : DeleteProductUseCase {
    override suspend fun execute(input: DeleteProductUseCase.Input): DeleteProductUseCase.Output {
        productRepository.deleteProduct(input.productId)
        return DeleteProductUseCase.Output.Success
    }
}