package com.tinyreel.authentication.data

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    suspend fun googleSignIn(credential: AuthCredential): Resource<FirebaseUser>
    fun logout();
}