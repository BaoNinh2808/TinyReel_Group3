package com.example.myprofile

import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.theme.R
import com.example.composable.TopBar
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material.icons.Icons
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import com.example.creatorprofile.screen.creatorprofile.CreatorProfileViewModel

import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.SavedStateHandle
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.data.repository.creatorprofile.CreatorProfileRepository
import com.example.domain.creatorprofile.GetCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingScreen(
    navController: NavController,
//    creatorProfileViewModel: CreatorProfileViewModel
) {
    val viewModel = MyProfileViewModel(
        savedStateHandle = SavedStateHandle(),
        getCreatorProfileUseCase = GetCreatorProfileUseCase(CreatorProfileRepository()),
        getCreatorPublicVideoUseCase = GetCreatorPublicVideoUseCase(CreatorProfileRepository())
    )
    viewModel.fetchUser(1)

    val entry = remember { mutableStateOf(navController.currentBackStackEntry) }

    DisposableEffect(entry.value) {
        val callback = NavController.OnDestinationChangedListener { controller, _, _ ->
            entry.value = controller.currentBackStackEntry
        }

        navController.addOnDestinationChangedListener(callback)

        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }

    val arguments = entry.value?.arguments
    val receivedViewModel: CreatorProfileViewModel? = arguments?.getViewModel("creatorProfileViewModel")

    var newName by remember { mutableStateOf("") }
    var newBio by remember { mutableStateOf("") }
    var newAvatarUrl by remember { mutableStateOf("") }
    var reloadImage by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopBar(
            navIcon = R.drawable.ic_arrow_back,
            onClickNavIcon = {
                 navController.popBackStack()
            },
            title = "Edit your profile",
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(0.3f)
                        .align(Alignment.CenterVertically),
                    text = "New user name"
                )
                TextField(
                    value = newName,
                    onValueChange = { newValue ->
                        newName = newValue
                        viewModel.updateUserName(newValue)
                    },
                    placeholder = { Text(text = "${viewModel.getUserModel()?.uniqueUserName}", color = Color.Gray) },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(0.3f)
                        .align(Alignment.CenterVertically),
                    text = "New bio"
                )
                TextField(
                    value = newBio,
                    onValueChange = { newValue ->
                        newBio = newValue
                    },
                    placeholder = { Text(text = "${viewModel.getUserModel()?.bio}", color = Color.Gray) },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(0.3f)
                        .align(Alignment.CenterVertically),
                    text = "Avatar URL"
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    value = newAvatarUrl,
                    onValueChange = { newValue ->
                        newAvatarUrl = newValue
                    },
                    placeholder = { Text(text = "New avatar URL...", color = Color.Gray) },
                )
                Button(
                    onClick = {
                        reloadImage = !reloadImage
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "$reloadImage")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                key(reloadImage) {
                    DisplayImage(imageUrl = newAvatarUrl, reloadImage = reloadImage)
                }
            }
        }
    }
}


@Composable
fun DisplayImage(imageUrl: String, reloadImage: Boolean) {
    Text(text = reloadImage.toString())
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primary),
    )
}