package com.tinyreel.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.tinyreel.authentication.data.AuthRepository
import com.tinyreel.authentication.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginWithEmailPhoneViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel<ViewState, LoginEmailPhoneEvent>() {
    private val _googleFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val googleFlow: StateFlow<Resource<FirebaseUser>?> = _googleFlow
    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        _googleFlow.value = Resource.Loading
        val result = repository.googleSignIn(credential)
        _googleFlow.value = result
    }
    private val _name = MutableStateFlow<String>("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow<String>("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow<String>("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init{
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }
    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun signup(name:String, email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password, confirmPassword)
        _signupFlow.value = result
    }

    fun logout(){
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
        _googleFlow.value = null
    }

    override fun onTriggerEvent(event: LoginEmailPhoneEvent) {
        when (event) {
            // is LoginEmailPhoneEvent.EventPageChange -> _settledPage.value = event.settledPage
            is LoginEmailPhoneEvent.OnChangeEmailEntry -> _email.value = event.newValue

            is LoginEmailPhoneEvent.OnChangePasswordEntry -> _password.value = event.newValue

            is LoginEmailPhoneEvent.OnChangeConfirmPasswordEntry -> _confirmPassword.value = event.newValue

            is LoginEmailPhoneEvent.OnChangeNameEntry -> _name.value = event.newValue

//            is LoginEmailPhoneEvent.OnChangePhoneNumber -> _phoneNumber.value =
//                _phoneNumber.value.copy(first = event.newValue)

        }
    }
}
