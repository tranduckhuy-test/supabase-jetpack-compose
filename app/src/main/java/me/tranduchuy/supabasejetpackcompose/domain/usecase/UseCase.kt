package me.tranduchuy.supabasejetpackcompose.domain.usecase

interface UseCase<InputT, OutputT> {
    suspend fun execute(input: InputT): OutputT
}