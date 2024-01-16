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
//    private val _settledPage = MutableStateFlow<Int?>(null)
//    val settledPage = _settledPage.asStateFlow()

//    private val _phoneNumber =
//        MutableStateFlow<Pair<String, String?>>(Pair("", null))  //Pair(value,errorMsg)
//    val phoneNumber = _phoneNumber.asStateFlow()

    //    private val _dialCode = MutableStateFlow<Pair<String, String?>>(Pair("Np +977", null))
//    val dialCode = _dialCode.asStateFlow()
//
//    private val _googleState = mutableStateOf(ViewState())
//    val googleState: State<ViewState> = _googleState
//    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
//        repository.googleSignIn(credential).collect{result ->
//            when(result){
//                is Resource.Success ->{
//                    _googleState.value = ViewState(success = result.result)
//                }
//                is Resource.Loading -> {
//                    _googleState.value = ViewState(isLoading = true)
//                }
//                is Resource.Failure -> {
//                    _googleState.value = ViewState(error(result.exception))
//                }
//            }
//        }
//    }
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
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

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

    fun signup(name:String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout(){
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
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

