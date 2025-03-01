package me.tranduchuy.supabasejetpackcompose.domain.usecase

interface DeleteProductUseCase: UseCase<DeleteProductUseCase.Input, DeleteProductUseCase.Output> {
    class Input(val productId: String)

    sealed class Output {
        object Success: Output()
    }
}