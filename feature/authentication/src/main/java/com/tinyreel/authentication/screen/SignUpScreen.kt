package com.tinyreel.authentication.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.constraintlayout.compose.ConstraintLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.DestinationRoute
import com.example.core.DestinationRoute.AUTHENTICATION_ROUTE
import com.example.core.DestinationRoute.SIGNUP_ROUTE
import com.example.theme.R
import com.example.theme.SubTextColor
import com.example.theme.fontFamily
import com.example.theme.spacing
import com.tinyreel.authentication.LoginEmailPhoneEvent
import com.tinyreel.authentication.LoginWithEmailPhoneViewModel
import com.tinyreel.authentication.data.AuthRepository
import com.tinyreel.authentication.data.Resource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(viewModel: LoginWithEmailPhoneViewModel, navController: NavController) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val name by viewModel.name.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    val signUpFlow = viewModel?.signupFlow?.collectAsState()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (refTitle, refDescribe, refBackground, refName, refEmail, refPassword, refConfirmPassword, refButtonSignup, refTextSignup, refGuess , refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

//        Box(
//            modifier = Modifier
//                .constrainAs(refHeader) {
//                    top.linkTo(parent.top, spacing.extraLarge)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    width = Dimension.fillToConstraints
//                }
//                .wrapContentSize()
//        ) {
//            AuthHeader()
//        }

        val imagePainter = painterResource(id = R.drawable.img_background)

        Image(
            painter = imagePainter,
            contentDescription = "Loaded Image",
            contentScale = ContentScale.Crop,
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
            text = stringResource(id = R.string.sign_up),
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

        TextField(
            value = name,
            onValueChange = {
                viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangeNameEntry(it))
            },
            label = {
                Text(text = stringResource(id = R.string.user_name))
            },
            modifier = Modifier.constrainAs(refName) {
                top.linkTo(refDescribe.bottom, spacing.extraLarge)
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
            value = email,
            onValueChange = {
                viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangeEmailEntry(it))
            },
            label = {
                Text(text = stringResource(id = R.string.enter_email_address_or_username))
            },
            modifier = Modifier.constrainAs(refEmail) {
                top.linkTo(refName.bottom, spacing.medium)
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
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    },
                ) {
                    Icon(
                        if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        TextField(
            value = confirmPassword,
            onValueChange = {
                viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangeConfirmPasswordEntry(it))
            },
            label = {
                Text(text = stringResource(id = R.string.confirmPassword))
            },
            modifier = Modifier.constrainAs(refConfirmPassword) {
                top.linkTo(refPassword.bottom, spacing.medium)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isConfirmPasswordVisible = !isConfirmPasswordVisible
                    },
                ) {
                    Icon(
                        if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Button(
            onClick = {
                viewModel?.signup(name, email, password, confirmPassword)
            },
            modifier = Modifier.constrainAs(refButtonSignup) {
                top.linkTo(refConfirmPassword.bottom, spacing.large)
                start.linkTo(parent.start, spacing.extraLarge)
                end.linkTo(parent.end, spacing.extraLarge)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.sign_up), style = MaterialTheme.typography.titleMedium)
        }

        Text(
            modifier = Modifier
                .constrainAs(refTextSignup) {
                    top.linkTo(refButtonSignup.bottom, spacing.medium)
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
        Text(
            modifier = Modifier
                .constrainAs(refGuess) {
                    top.linkTo(refTextSignup.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                }
                .clickable {
                    navController.navigate(DestinationRoute.HOME_SCREEN_ROUTE) {
                        popUpTo(DestinationRoute.HOME_SCREEN_ROUTE) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.Guess_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        signUpFlow?.value?.let {
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
                    navController.navigate(DestinationRoute.HOME_SCREEN_ROUTE){
                        popUpTo(DestinationRoute.HOME_SCREEN_ROUTE) {inclusive = true}
                    }
                }

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    val navController = rememberNavController()
//    SignupScreen(null , navController = navController)
//}