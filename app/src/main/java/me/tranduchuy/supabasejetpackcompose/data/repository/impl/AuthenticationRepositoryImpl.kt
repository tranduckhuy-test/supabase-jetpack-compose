package me.tranduchuy.supabasejetpackcompose.data.repository.impl

import android.util.Log
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import me.tranduchuy.supabasejetpackcompose.data.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signInWithGoogle(): Boolean {
        return try {
            Log.d("AuthenticationRepository", "Signing out before Google sign-in")
            auth.signOut()

            Log.d("AuthenticationRepository", "Attempting Google sign-in")
            auth.signInWith(Google)

            val user = auth.currentUserOrNull()
            Log.d("AuthenticationRepository", "User after Google sign-in: $user")

            val success = user != null
            Log.d("AuthenticationRepository", "Google sign-in success: $success")
            success
        } catch (e: Exception) {
            Log.e("AuthenticationRepository", "Google sign-in failed: ${e.message}")
            false
        }
    }

}
