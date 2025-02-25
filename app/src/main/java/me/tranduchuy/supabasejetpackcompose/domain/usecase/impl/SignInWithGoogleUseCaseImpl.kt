package me.tranduchuy.supabasejetpackcompose.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tranduchuy.supabasejetpackcompose.data.repository.AuthenticationRepository
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignInWithGoogleUseCase
import javax.inject.Inject

class SignInWithGoogleUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
): SignInWithGoogleUseCase {
    override suspend fun execute(input: SignInWithGoogleUseCase.Input): SignInWithGoogleUseCase.Output {
        return withContext(Dispatchers.IO) {
            authenticationRepository.signInWithGoogle()
            SignInWithGoogleUseCase.Output()
        }
    }
}