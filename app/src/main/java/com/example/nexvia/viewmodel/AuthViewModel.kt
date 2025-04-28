package com.example.nexvia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexvia.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun registerPasajero(nombre: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.registerPasajero(nombre, email, password)
            _authState.value = if (result.isSuccess) AuthState.Success else AuthState.Error(result.exceptionOrNull()?.message)
        }
    }

    fun registerConductor(nombre: String, email: String, password: String, placa: String, ruta: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.registerConductor(nombre, email, password, placa, ruta)
            _authState.value = if (result.isSuccess) AuthState.Success else AuthState.Error(result.exceptionOrNull()?.message)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.login(email, password)
            _authState.value = if (result.isSuccess) AuthState.LoginSuccess(result.getOrThrow()) else AuthState.Error(result.exceptionOrNull()?.message)
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState() // Registro exitoso
    data class LoginSuccess(val rol: String) : AuthState() // Login exitoso con rol
    data class Error(val message: String?) : AuthState()
}