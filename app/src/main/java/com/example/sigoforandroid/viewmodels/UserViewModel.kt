package com.example.sigoforandroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigoforandroid.data.model.User
import com.example.sigoforandroid.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login(matricula: String, password: String): Boolean {
        val user = UserRepository.authenticate(matricula, password)
        if (user != null) {
            _currentUser.value = user
            _isLoggedIn.value = true
            return true
        }
        return false
    }

    fun logout() {
        _currentUser.value = null
        _isLoggedIn.value = false
    }

    fun getCurrentUser(): User? = _currentUser.value

    fun getAverageGrade(userId: Int): Float {
        return UserRepository.getAverageGradeByUserId(userId)
    }

    fun getCurrentSubjectsCount(userId: Int): Int {
        return UserRepository.getCurrentSubjectsCountByUserId(userId)
    }

    fun getUserFullName(userId: Int): String {
        val user = UserRepository.getUserById(userId)
        return if (user != null) {
            "${user.nombres} ${user.primerApellido} ${user.segundoApellido}"
        } else {
            "Usuario no encontrado"
        }
    }
}