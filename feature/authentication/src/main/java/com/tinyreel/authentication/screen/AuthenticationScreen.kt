package com.tinyreel.authentication.screen

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
// import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composable.CustomIconButton
import com.example.core.AppContract.Annotate.ANNOTATED_PRIVACY_POLICY
import com.example.core.AppContract.Annotate.ANNOTATED_TAG
import com.example.core.AppContract.Annotate.ANNOTATED_TERMS_OF_SERVICE
import com.example.core.extension.Space
import com.example.theme.R
import com.example.theme.SubTextColor
import com.example.theme.fontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composable.CustomButton
import com.example.core.DestinationRoute.AUTHENTICATION_ROUTE
import com.example.core.DestinationRoute.HOME_SCREEN_ROUTE
import com.example.core.DestinationRoute.SIGNUP_ROUTE
import com.example.theme.spacing
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.tinyreel.authentication.LoginEmailPhoneEvent
import com.tinyreel.authentication.LoginOption
import com.tinyreel.authentication.LoginWithEmailPhoneViewModel
import com.tinyreel.authentication.data.AuthRepository
import com.tinyreel.authentication.data.Resource
import com.tinyreel.authentication.serverClientID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel:LoginWithEmailPhoneViewModel, navController: NavController) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    val loginFlow = viewModel?.loginFlow?.collectAsState()
    val context = LocalContext.current

    val googleFlow = viewModel?.googleFlow?.collectAsState()

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){
        val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try{
            print("Haha")
            val result = account.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
            viewModel.googleSignIn(credentials)
        }catch (it: ApiException){
            print(it)
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (refTitle, refDescribe, refBackground, refLoginOftion, refEmail, refPassword, refButtonLogin, refTextSignup, refGuess, refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

        val imagePainter = painterResource(id = R.drawable.img_background)
        Image(
            painter = imagePainter,
            contentDescription = "Loaded Image",
            modifier = Modifier
                .constrainAs(refBackground) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .fillMaxSize(1f)
        )

        Text(
            modifier = Modifier
                .constrainAs(refTitle) {
                    top.linkTo(parent.top, spacing.extraLarge)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = R.string.login),
            style = TextStyle(
                color = Color.White,
                fontFamily = fontFamily,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(refDescribe) {
                    top.linkTo(refTitle.bottom, spacing.medium)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = stringResource(id = R.string.login_welcome),
            color = SubTextColor,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            )
        )
//
//        AuthenticationButton(
//            modifier = Modifier
//                .constrainAs(refLoginOftion) {
//                    top.linkTo(refDescribe.bottom, spacing.medium)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    width = Dimension.fillToConstraints
//                }, viewModel)

        CustomIconButton(
            buttonText = stringResource(id = LoginOption.GOOGLE.title),
            icon = LoginOption.GOOGLE.icon,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .constrainAs(refLoginOftion) {
                    top.linkTo(refDescribe.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                    width = Dimension.fillToConstraints
                },
            containerColor = LoginOption.GOOGLE.containerColor,
            contentColor = LoginOption.GOOGLE.contentColor,
            onClickButton = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(serverClientID)
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }
        )

        TextField(
            value = email,
            onValueChange = {
                viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangeEmailEntry(it))
            },
            label = {
                Text(text = stringResource(id = R.string.user_name))
            },
            modifier = Modifier.constrainAs(refEmail) {
                top.linkTo(refLoginOftion.bottom, spacing.extraLarge)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = password,
            onValueChange = {
                viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangePasswordEntry(it))
            },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            modifier = Modifier.constrainAs(refPassword) {
                top.linkTo(refEmail.bottom, spacing.medium)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Button(
            onClick = {
                viewModel?.login(email, password)
            },
            modifier = Modifier.constrainAs(refButtonLogin) {
                top.linkTo(refPassword.bottom, spacing.large)
                start.linkTo(parent.start, spacing.extraLarge)
                end.linkTo(parent.end, spacing.extraLarge)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.login), style = MaterialTheme.typography.labelLarge)
        }


        Text(
            modifier = Modifier
                .constrainAs(refTextSignup) {
                    top.linkTo(refButtonLogin.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                }
                .clickable {
                    navController.navigate(SIGNUP_ROUTE) {
                        popUpTo(AUTHENTICATION_ROUTE) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.sign_up_for_an_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier
                .constrainAs(refGuess) {
                    top.linkTo(refTextSignup.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                }
                .clickable {
                    navController.navigate(AUTHENTICATION_ROUTE) {
                        popUpTo(SIGNUP_ROUTE) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.Already_has_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        googleFlow?.value?.let {
            when(it){
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
                is Resource.Success -> {
                    navController.navigate(HOME_SCREEN_ROUTE){
                        popUpTo(HOME_SCREEN_ROUTE) {inclusive = true}
                    }
                }

            }
        }

        loginFlow?.value?.let {
            when(it){
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
                is Resource.Success -> {
                    navController.navigate(HOME_SCREEN_ROUTE){
                        popUpTo(HOME_SCREEN_ROUTE) {inclusive = true}
                    }
                }

            }
        }
    }
}
