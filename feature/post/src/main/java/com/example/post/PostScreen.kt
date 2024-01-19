package com.example.post

import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.documentfile.provider.DocumentFile
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import com.example.composable.CustomButton
import com.example.composable.TopBar
import com.example.core.DestinationRoute.HOME_SCREEN_ROUTE
import com.example.data.model.VideoModel
import com.example.data.source.UsersDataSource.userList
import com.example.theme.R
import com.example.theme.Typography
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
//import com.google.firebase.storage.FirebaseStorage

val databaseReference = FirebaseDatabase.getInstance().getReference()
val storageReference = Firebase.storage.reference
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(navController: NavController) {
    var imgUri by remember { mutableStateOf<Uri?>(null)}
    val fileLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = {
                imgUri = it.firstOrNull()
            })
    var textValue by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                navIcon = R.drawable.ic_cancel,
                title = stringResource(id = R.string.create_post),
                onClickNavIcon = { navController.navigateUp() },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            DescriptionAndImage(
                textValue = textValue,
                imgUri,
                onTextValueChange = { newText ->
                    textValue = newText
                },
                onClicked = {
                    fileLauncher.launch(pickVisualMediaRequest)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            DisplayTags(navController)

            Spacer(modifier = Modifier.height(10.dp))

            PrivacySetting("Ai được xem video này?")

            Spacer(modifier = Modifier.height(10.dp))

            PrivacySetting("Ai có thể bình luận?")

            Spacer(modifier = Modifier.height(160.dp))

            PostButton(
                onClicked = {
                    if (imgUri == null) {
                        Toast.makeText(context, "Please choose an image!", Toast.LENGTH_SHORT).show()
                        return@PostButton
                    }
                    val file = DocumentFile.fromSingleUri(context, imgUri!!)
                    val fileName = file!!.name
                    val fileNameAsId = fileName!!.substringBeforeLast(".")
                    val randUser = userList.random()

                    val fileRef = storageReference.child(fileName)
                    val uploadTask = fileRef.putFile(imgUri!!)
                    uploadTask
                        .addOnSuccessListener {
                            Toast.makeText(context, "Upload image successfully!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Upload image failed!", Toast.LENGTH_SHORT).show()
                        }

                    val curVideo = VideoModel(
                        videoId = fileNameAsId,
                        authorDetails = randUser,
                        videoLink = fileName,
                        videoStats = VideoModel.VideoStats(
                            like = 0,
                            comment = 0,
                            share = 0,
                            views = 0
                        ),
                        description = "Draft video testing  #foryou #fyp #compose #tik",
                        audioModel = null, hasTag = listOf(),
                    )
                    databaseReference.child("TinyReel").child("forYou")
                        .child("videos").push().setValue(curVideo)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Post video successfully!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Post video failed!", Toast.LENGTH_SHORT).show()
                        }
                    imgUri = null
                }
            )
        }
    }
}

@Composable
fun DescriptionAndImage(
    textValue: String,
    imgUri: Uri? = null,
    onTextValueChange: (String) -> Unit, // Changed the function signature
    onClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        var isTextVisible by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color(0xFFD2F3F2),
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the TextField
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFFAAE9E6),
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the border
                )
        ) {
            BasicTextField(
                value = textValue,
                onValueChange = { newText ->
                    onTextValueChange(newText) // Use the provided function to update textValue
                    if (newText.isEmpty()) {
                        isTextVisible = true // If value is empty, hide the text
                    } else {
                        isTextVisible = false // If value is not empty, show the text
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp, top = 20.dp)
                    .background(
                        color = Color.Transparent // Make the TextField background transparent
                    ),
                textStyle = Typography.displaySmall,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        content = { innerTextField() }
                    )
                }
            )

            if (isTextVisible) {
                Text(
                    text = "Mô tả nội dung",
                    modifier = Modifier
                        .padding(start = 12.dp, top = 20.dp)
                        .background(Color.Transparent)
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Placeholder for the image and text box overlap
        Box(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)
                .fillMaxWidth()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the Box
                )
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the border
                )
                .clickable { onClicked() }
        ) {
            // Image
            if (imgUri != null) {
                val contentResolver = LocalContext.current.contentResolver
                val mimeTypeMap = MimeTypeMap.getSingleton()
                val fileType = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imgUri))
                if (fileType == "jpg") {
                    AsyncImage(
                        model = imgUri,
                        contentDescription = "Loaded Image",
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center)
                            .clip(shape = RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                }
                else {
                    val model = ImageRequest.Builder(LocalContext.current)
                        .data(imgUri)
                        .videoFrameMillis(10000)
                        .decoderFactory { result, options, _ ->
                            VideoFrameDecoder(
                                result.source,
                                options
                            )
                        }
                        .build()
                    AsyncImage(
                        model = model,
                        contentDescription = "Loaded Image",
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center)
                            .clip(shape = RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                }

            }
            else {
                val imagePainter = painterResource(id = R.drawable.ic_add)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center),
                )

                // Text
                // Box for text with background color
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0x35D2F3F2),
                            shape = RoundedCornerShape(16.dp) // Set rounded corners for the Box
                        )
                ) {
                    Text(
                        text = "Chọn ảnh\nnổi bật",
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Center),
                        style = Typography.displayMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Composable
fun DisplayTags(navController: NavController) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableTextWithIcon(
            textValue = "Nhắc đến",
            iconId = R.drawable.ic_add,
            onClicked = { navController.navigate(HOME_SCREEN_ROUTE) },
            modifier = Modifier.weight(1f) // Set equal weight for each tag
        )
        ClickableTextWithIcon(
            textValue = "Hashtag",
            iconId = R.drawable.ic_msg,
            onClicked = { navController.navigate(HOME_SCREEN_ROUTE) },
            modifier = Modifier.weight(1f) // Set equal weight for each tag
        )
        ClickableTextWithIcon(
            textValue = "Vị trí",
            iconId = R.drawable.ic_home,
            onClicked = { navController.navigate(HOME_SCREEN_ROUTE) },
            modifier = Modifier.weight(1f) // Set equal weight for each tag
        )
    }
}

@Composable
fun ClickableTextWithIcon(
    textValue: String,
    iconId: Int,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier // Added a default modifier parameter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClicked() }
            .padding(16.dp)
    ) {
        Text(
            text = textValue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(5.dp)) // Adjust the space between Text and Icon

        IconButton(
            onClick = { onClicked() },
            modifier = Modifier.size(24.dp)
        ) {
            // Your icon here
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Icon",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun PrivacySetting(decribeText : String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Text(
            text = decribeText,
            style = Typography.bodyMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(10.dp))

        DropdownComponent()
    }

}

@Composable
fun DropdownComponent() {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Mọi người") }
    val items = listOf("Mọi người", "Follower", "Chỉ Mình Tôi")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Dropdown trigger text
        Text(
            text = selectedItem,
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedItem = item
                    expanded = false
                }) {
                    Text(text = item) // Display the item's text
                }
            }
        }
    }
}

@Composable
fun PostButton(onClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(
            buttonText = stringResource(id = R.string.post),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth(0.65f),
        ) {
            onClicked()
        }
    }

}

@Preview
@Composable
fun PostScreenPreview() {
    // Create a preview using the PostScreen composable
    // For example, you can pass a NavController instance to simulate usage
    val navController = rememberNavController()
    PostScreen(navController = navController)
}

val pickVisualMediaRequest by lazy {
    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
}