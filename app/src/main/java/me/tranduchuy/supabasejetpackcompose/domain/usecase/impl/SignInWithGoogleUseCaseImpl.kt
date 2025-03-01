package me.tranduchuy.supabasejetpackcompose.domain.usecase.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tranduchuy.supabasejetpackcompose.data.repository.AuthenticationRepository
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignInWithGoogleUseCase
import javax.inject.Inject

class SignInWithGoogleUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : SignInWithGoogleUseCase {
    override suspend fun execute(input: SignInWithGoogleUseCase.Input): SignInWithGoogleUseCase.Output {
        return withContext(Dispatchers.IO) {
            Log.d("AuthenticationRepository", "Starting Google sign-in use case")
            val result = authenticationRepository.signInWithGoogle()
            Log.d("AuthenticationRepository", "Google sign-in result in use case: $result")

            SignInWithGoogleUseCase.Output(success = result)
        }
    }
}