package me.tranduchuy.supabasejetpackcompose.presentation.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignInUseCase
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignInWithGoogleUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : ViewModel() {

    private val _email = MutableStateFlow<String>("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _message = MutableStateFlow("")
    val message = _message

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onGoogleSignIn() {
        viewModelScope.launch {
            signInWithGoogleUseCase.execute(SignInWithGoogleUseCase.Input())
        }
    }

    fun onSignIn() {
        viewModelScope.launch {
            val result = signInUseCase.execute(
                SignInUseCase.Input(
                    email = _email.value,
                    password = _password.value
                )
            )
            when (result) {
                is SignInUseCase.Output.Success -> {
                    message.emit("Login successfully !")
                }
                else -> {
                    message.emit("Login failed !")
                }
            }
        }
    }
}