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
import com.example.domain.creatorprofile.EditableCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingScreen(
    navController: NavController,
) {
    val viewModel = MyProfileViewModel(
//        savedStateHandle = SavedStateHandle(),
        userId = 1,
        getCreatorProfileUseCase = GetCreatorProfileUseCase(CreatorProfileRepository()),
        getCreatorPublicVideoUseCase = GetCreatorPublicVideoUseCase(CreatorProfileRepository())
    )
//    viewModel.fetchUser(1)
    val viewState by viewModel.viewState.collectAsState()

    var newName by remember { mutableStateOf("") }
    var newBio by remember { mutableStateOf("") }
    var newAvatarUrl by remember { mutableStateOf("") }
    var reloadImage by remember { mutableStateOf(false) }

    var nameChanged = false
    var bioChanged = false
    var avatarChanged = false

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
                        nameChanged = true
                    },
                    placeholder = { Text(text = "${viewState?.creatorProfile?.uniqueUserName?:"New user name"}", color = Color.Gray) },
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
                        bioChanged = true
                    },
                    placeholder = { Text(text = "${viewState?.creatorProfile?.bio?:"New bio"}", color = Color.Gray) },
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
                        avatarChanged = true
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

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//            ) {
//                key(reloadImage) {
//                    DisplayImage(imageUrl = newAvatarUrl, reloadImage = reloadImage)
//                }
//            }

//            Button(
//                onClick = { ->
//                    if (nameChanged) {
//                        viewModel.updateProfile("username", newName)
//                    }
//                    if (bioChanged) {
//                        viewModel.updateProfile("bio", newBio)
//                    }
//                    if (avatarChanged) {
//                        viewModel.updateProfile("avatar", "https://images.unsplash.com/photo-1575936123452-b67c3203c357?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D")
//                    }
//                },
//                modifier = Modifier
//                    .padding(16.dp)
//            ) {
//                Text("Commit changes")
//            }
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