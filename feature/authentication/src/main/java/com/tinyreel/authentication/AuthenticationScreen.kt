package com.tinyreel.authentication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
// import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composable.CustomIconButton
import com.example.core.AppContract.Annotate.ANNOTATED_PRIVACY_POLICY
import com.example.core.AppContract.Annotate.ANNOTATED_TAG
import com.example.core.AppContract.Annotate.ANNOTATED_TERMS_OF_SERVICE
import com.example.core.DestinationRoute.LOGIN_OR_SIGNUP_WITH_PHONE_EMAIL_ROUTE
import com.example.core.extension.Space
import com.example.theme.Black
import com.example.theme.R
import com.example.theme.SeparatorColor
import com.example.theme.SubTextColor
import com.example.theme.fontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composable.CustomButton
import com.tinyreel.authentication.LoginOption
import com.tinyreel.authentication.tabs.EmailUsernameTabScreen
import com.tinyreel.authentication.tabs.PhoneTabScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch


@Composable
fun AuthenticationScreen(
    navController: NavController,
    viewModel: LoginWithEmailPhoneViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        val imagePainter = painterResource(id = R.drawable.img_background)
        Image(
            painter = imagePainter,
            contentDescription = "Loaded Image",
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 0.dp),
            // .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //        Row(
            //            modifier = Modifier
            //                .fillMaxWidth()
            //                .padding(top = 16.dp),
            //            horizontalArrangement = Arrangement.SpaceBetween,
            //            verticalAlignment = Alignment.CenterVertically
            //        ) {
            //            Icon(painter = painterResource(id = R.drawable.ic_cancel), contentDescription = null,
            //                modifier = Modifier
            //                    .clickable { navController.navigateUp() }
            //                    .size(24.dp)
            //            )
            //            Icon(
            //                painter = painterResource(id = R.drawable.ic_question_circle),
            //                contentDescription = null
            //            )
            //
            //        }

            56.dp.Space()
            Text(
                text = stringResource(id = R.string.login),
                style = TextStyle(
                    color = Color.White,
                    fontFamily = fontFamily,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )

            )
            20.dp.Space()
            Text(
                text = stringResource(id = R.string.login_welcome),
                color = SubTextColor,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                )
            )

            28.dp.Space()
            AuthenticationButton {
                //            when (it) {
                //                LoginOption.PHONE_EMAIL_USERNAME -> navController.navigate(
                //                    LOGIN_OR_SIGNUP_WITH_PHONE_EMAIL_ROUTE
                //                )
                //                else -> {}
                //            }
            }
            100.dp.Space()
            UsernameField(email, viewModel)

            50.dp.Space()
            PasswordField(password, viewModel)
            CustomButton(
                buttonText = stringResource(id = R.string.login),
                modifier = Modifier.fillMaxWidth(),
                isEnabled = email.first.isNotEmpty()
            ) {

            }
            Spacer(modifier = Modifier.weight(1f))
            PrivacyPolicyFooter()
        }
    }
}

@Composable
internal fun AuthenticationButton(onClickButton: (LoginOption) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        LoginOption.values().asList().forEach {
            CustomIconButton(
                buttonText = stringResource(id = it.title),
                icon = it.icon,
                modifier = Modifier.weight(1f),
                containerColor = it.containerColor,
                contentColor = it.contentColor,
            ) {
                onClickButton(it)
            }
            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa các nút
        }
    }
}

@Composable
fun PrivacyPolicyFooter() {
    val spanStyle = SpanStyle(
        fontSize = 13.sp,
        color = Color.Black,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
    val annotatedString = buildAnnotatedString {
        append(stringResource(id = R.string.by_continuing_you_agree).plus(" "))
        pushStringAnnotation(
            tag = ANNOTATED_TAG, annotation = ANNOTATED_TERMS_OF_SERVICE
        )
        withStyle(
            style = spanStyle
        ) {
            append(stringResource(id = R.string.terms_of_service))
        }
        pop()
        append(" ".plus(stringResource(id = R.string.and_acknowledge_that_you_have)).plus(" "))
        pushStringAnnotation(
            tag = ANNOTATED_TAG, annotation = ANNOTATED_PRIVACY_POLICY
        )
        withStyle(
            style = spanStyle
        ) {
            append(stringResource(id = R.string.privacy_policy))
        }
        pop()
        append(" ".plus(stringResource(id = R.string.to_learn_how_we_collect)))

    }

    ClickableText(
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = ANNOTATED_TAG, start = offset, end = offset
            ).firstOrNull()?.let { annotation ->
                when (annotation.item) {
                    ANNOTATED_TERMS_OF_SERVICE -> {

                    }
                    ANNOTATED_PRIVACY_POLICY -> {

                    }
                }
            }
        }, style = TextStyle(
            color = SubTextColor,
            fontFamily = fontFamily,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    )

}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//internal fun LoginPager(viewModel: LoginWithEmailPhoneViewModel) {
//    val pagerState = rememberPagerState()
//    val pages = LoginPages.values().asList()
//    val coroutineScope = rememberCoroutineScope()
//    LaunchedEffect(key1 = pagerState) {
//        snapshotFlow { pagerState.settledPage }.collect {
//            viewModel.onTriggerEvent(LoginEmailPhoneEvent.EventPageChange(it))
//        }
//    }
//    TabRow(
//        selectedTabIndex = pagerState.currentPage,
//        indicator = {
//            val modifier = Modifier
//                .tabIndicatorOffset(it[pagerState.currentPage])
//                .padding(horizontal = 26.dp)
//            TabRowDefaults.Indicator(modifier, color = Black)
//        },
//        divider = {
//            Divider(thickness = 0.5.dp, color = SeparatorColor)
//        },
//    ) {
//        pages.forEachIndexed { index, item ->
//            val isSelected = pagerState.currentPage == index
//            Tab(
//                selected = isSelected,
//                onClick = {
//                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
//                },
//                selectedContentColor = Color.Black,
//                unselectedContentColor = SubTextColor,
//                text = {
//                    Text(
//                        text = stringResource(id = item.title),
//                        style = MaterialTheme.typography.bodySmall
//                    )
//                }
//            )
//        }
//    }
//
//    HorizontalPager(
//        modifier = Modifier.fillMaxSize(),
//        state = pagerState,
//        pageCount = pages.size,
//        key = { it }
//    ) { page ->
//
//        when (page) {
//            0 -> PhoneTabScreen(viewModel)
//            1 -> EmailUsernameTabScreen(viewModel)
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameField(email: Pair<String, String?>, viewModel: LoginWithEmailPhoneViewModel) {
    val currentPage by viewModel.settledPage.collectAsState()
    val focusRequester = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = email.first,
        textStyle = MaterialTheme.typography.labelLarge,
        onValueChange = { viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangeEmailEntry(it)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = SubTextColor,
            unfocusedIndicatorColor = SubTextColor,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.user_name),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                )
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                viewModel.onTriggerEvent(
                    LoginEmailPhoneEvent.OnChangeEmailEntry(
                        ""
                    )
                )
            }) {
                if (email.first.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null
                    )
                }

            }
        }
    )
    LaunchedEffect(key1 = currentPage) {
        if (currentPage == 1) {
            focusRequester.requestFocus()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: Pair<String, String?>, viewModel: LoginWithEmailPhoneViewModel) {
    val currentPage by viewModel.settledPage.collectAsState()
    val focusRequester = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = password.first,
        textStyle = MaterialTheme.typography.labelLarge,
        onValueChange = { viewModel.onTriggerEvent(LoginEmailPhoneEvent.OnChangePasswordEntry(it)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = SubTextColor,
            unfocusedIndicatorColor = SubTextColor,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.password),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                )
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                viewModel.onTriggerEvent(
                    LoginEmailPhoneEvent.OnChangePasswordEntry(
                        ""
                    )
                )
            }) {
                if (password.first.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null
                    )
                }

            }
        }
    )
    LaunchedEffect(key1 = currentPage) {
        if (currentPage == 1) {
            focusRequester.requestFocus()
        }
    }
}



@Preview
@Composable
fun AuthenticationScreenPreview() {
    val navController = rememberNavController()
    AuthenticationScreen(navController = navController)
}