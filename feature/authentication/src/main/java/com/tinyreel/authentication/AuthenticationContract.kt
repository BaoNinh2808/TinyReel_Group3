package com.tinyreel.authentication

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.theme.Black
import com.example.theme.PrimaryColor
import com.example.theme.R
import com.example.theme.White
import com.google.firebase.auth.AuthResult
import com.tinyreel.authentication.data.Resource

class AuthenticationContract {
}

const val serverClientID = "1002342456204-9qkhvvqsame9m4r5p4an0i8m45nv5ipr.apps.googleusercontent.com"
enum class LoginOption(
    @DrawableRes var icon: Int,
    @StringRes val title: Int,
    val containerColor: Color = Color.White,
    val contentColor: Color = Black

) {
    //    PHONE_EMAIL_USERNAME(
//        icon = R.drawable.ic_profile,
//        title = R.string.use_phone_email_username,
//        containerColor = PrimaryColor,
//        contentColor = White
//    ),
    FACEBOOK(
        icon = R.drawable.ic_facebook,
        title = R.string.continue_with_facebook,
    ),
    GOOGLE(
        icon = R.drawable.ic_google,
        title = R.string.continue_with_google,
    ),
//    TWITTER(
//        icon = R.drawable.ic_twitter,
//        title = R.string.continue_with_twitter,
//    ),
}

data class ViewState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val success: AuthResult? = null
)


sealed class LoginEmailPhoneEvent {
    // data class EventPageChange(val settledPage: Int) : LoginEmailPhoneEvent()
    // data class OnChangePhoneNumber(val newValue: String) : LoginEmailPhoneEvent()
    data class OnChangeEmailEntry(val newValue: String) : LoginEmailPhoneEvent()

    data class OnChangePasswordEntry(val newValue: String) : LoginEmailPhoneEvent()
    data class OnChangeConfirmPasswordEntry(val newValue: String) : LoginEmailPhoneEvent()
    data class OnChangeNameEntry(val newValue: String) : LoginEmailPhoneEvent()
}


enum class LoginPages(@StringRes val title: Int) {
    EMAIL_USERNAME(title = R.string.user_name),
    PASSWORD(title = R.string.password)
}

val suggestedDomainList =
    arrayListOf("@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com", "@icloud.com")