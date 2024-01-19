package com.example.camera.tabs

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.camera.CameraCaptureOptions
import com.example.camera.CameraController
import com.example.camera.CameraMediaViewModel
import com.example.camera.PermissionType
import com.example.camera.Tabs
import com.example.composable.CaptureButton
import com.example.core.DestinationRoute.POST_ROUTE
import com.example.core.extension.MediumSpace
import com.example.core.extension.Space
import com.example.core.utils.openAppSetting
import com.example.theme.LightGreenColor
import com.example.theme.R
import com.example.theme.TealColor
import com.example.theme.White
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: CameraMediaViewModel,
    cameraOpenType: Tabs = Tabs.CAMERA
) {
    val context = LocalContext.current
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO
        )
    )
    LaunchedEffect(key1 = Unit) {
        if (!multiplePermissionState.permissions[0].status.isGranted) {
            multiplePermissionState.launchMultiplePermissionRequest()
        }
    }

    val fileLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = {})


    Column(modifier = Modifier.fillMaxSize()) {
        if (multiplePermissionState.permissions[0].status.isGranted) {
            CameraPreview(cameraOpenType,
                onClickCancel = { navController.navigateUp() },
                onClickOpenFile = {
                    fileLauncher.launch(pickVisualMediaRequest)
                }, navController = navController
            )
        } else {
            CameraMicrophoneAccessPage(multiplePermissionState.permissions[1].status.isGranted,
                cameraOpenType,
                onClickCancel = { navController.navigateUp() },
                onClickOpenFile = { fileLauncher.launch(pickVisualMediaRequest) }) {
                val permissionState = when (it) {
                    PermissionType.CAMERA -> multiplePermissionState.permissions[1]
                    PermissionType.MICROPHONE -> multiplePermissionState.permissions[1]
                }
                permissionState.apply {
                    if (this.status.shouldShowRationale) {
                        this.launchPermissionRequest()
                    } else {
                        context.openAppSetting()
                    }
                }
            }
        }
    }
}


@Composable
fun CameraMicrophoneAccessPage(
    isMicrophonePermissionGranted: Boolean,
    cameraOpenType: Tabs,
    onClickCancel: () -> Unit,
    onClickOpenFile: () -> Unit,
    onClickGrantPermission: (PermissionType) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        TealColor, LightGreenColor
                    )
                )
            )
            .padding(top = 32.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_cancel),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .align(Alignment.Start)
                .clickable { onClickCancel() })
        MediumSpace()

        Text(
            text = stringResource(id = R.string.allow_tiktok_to_access_your),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.75f)
        )
        MediumSpace()

        Text(
            text = "${stringResource(id = R.string.take_photos_record_videos_or_preview)} ${
                stringResource(id = R.string.you_can_change_preference_in_setting)
            }",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.75f),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
        30.dp.Space()

        Card(
            modifier = Modifier.fillMaxWidth(0.75f), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically)
            ) {
                Row(
                    modifier = Modifier.clickable {
                        onClickGrantPermission(PermissionType.CAMERA)
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.access_camera),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                if (!isMicrophonePermissionGranted) {
                    Divider(color = White.copy(alpha = 0.2f))
                    Row(
                        modifier = Modifier.clickable {
                            onClickGrantPermission(PermissionType.MICROPHONE)
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_microphone),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = R.string.access_microhpone),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FooterCameraController(
            cameraOpenType = cameraOpenType,
            onClickEffect = { },
            onClickOpenFile = onClickOpenFile,
            onClickImageCapture = { },
            onClickVideoCapture = { },
            isEnabledLayout = false
        )
    }
}

@Composable
fun CameraPreview(
    cameraOpenType: Tabs,
    onClickCancel: () -> Unit,
    onClickOpenFile: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var defaultCameraFacing by remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
    val cameraProvider = cameraProviderFuture.get()
    val preview = remember { Preview.Builder().build() }
    val imageCapture = remember { ImageCapture.Builder().build() }

    val recorder = Recorder.Builder()
        .setQualitySelector(QualitySelector.from(Quality.HIGHEST,
            FallbackStrategy.higherQualityOrLowerThan(Quality.SD)))
        .build()
    val videoCapture = VideoCapture.withOutput(recorder)
    var recording: Recording? = null

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                val cameraPreview = PreviewView(it)
                cameraProviderFuture.addListener({
                    preview.also {
                        it.setSurfaceProvider(cameraPreview.surfaceProvider)
                    }
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(lifecycleOwner, defaultCameraFacing, preview, imageCapture, videoCapture)
                    } catch (e: Exception) {
                        Log.e("camera", "camera preview exception :${e.message}")
                    }
                }, ContextCompat.getMainExecutor(context))
                cameraPreview
            }, modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
        ) {
            FooterCameraController(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                cameraOpenType = cameraOpenType,
                onClickEffect = {navController.navigate(POST_ROUTE)},
                onClickOpenFile = onClickOpenFile,
                onClickImageCapture = {
                    captureImage(
                        context,
                        imageCapture
                    )
                },
                onClickVideoCapture = {
                    val curRecording = recording
                    if (curRecording != null) {
                        curRecording.stop()
                        recording = null
                        return@FooterCameraController
                    }

                    val name = "CameraX-recording-" +
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                .format(System.currentTimeMillis()) + ".mp4"
                    val contentValues = ContentValues().apply {
                        put(MediaStore.Video.Media.DISPLAY_NAME, name)
                    }
                    val mediaStoreOutput = MediaStoreOutputOptions.Builder(context.contentResolver,
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                        .setContentValues(contentValues)
                        .build()

                    recording = videoCapture.output
                        .prepareRecording(context, mediaStoreOutput)
                        .apply {
                            if (ActivityCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.RECORD_AUDIO
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                withAudioEnabled()
                            }
                        }
                        .start(ContextCompat.getMainExecutor(context)) {
                            when (it) {
                                is VideoRecordEvent.Start -> {
                                    val msg = "Recording video started!"
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                }
                                is VideoRecordEvent.Finalize -> {
                                    if (!it.hasError()) {
                                        val msg = "Video Capture Succeeded: " + "${it.outputResults.outputUri}"
                                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                                    }
                                    else {
                                        recording?.close()
                                        recording = null
                                        Log.d("error", it.error.toString())
                                    }
                                }
                            }
                        }
                },
                isEnabledLayout = true
            )

            CameraSideControllerSection(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 10.dp),
                defaultCameraFacing
            ) {
                when (it) {
                    CameraController.FLIP -> {
                        defaultCameraFacing =
                            if (defaultCameraFacing == CameraSelector.DEFAULT_FRONT_CAMERA) CameraSelector.DEFAULT_BACK_CAMERA else CameraSelector.DEFAULT_FRONT_CAMERA
                    }
                    else -> {}
                }
            }

            LaunchedEffect(key1 = defaultCameraFacing) {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, defaultCameraFacing, preview, imageCapture, videoCapture
                )
            }

            Icon(painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp)
                    .size(24.dp)
                    .clickable {
                        onClickCancel()
                    })

            Row(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_music_note),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = stringResource(id = R.string.add_sound),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }

}

private fun captureImage(
    context: Context,
    imageCapture: ImageCapture
) {
    val name = "CameraX-photo-" +
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .format(System.currentTimeMillis()) + ".jpg"
    val contentValues = ContentValues().apply {
        put(MediaStore.Video.Media.DISPLAY_NAME, name)
    }
    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        .build()

    imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val msg = "Photo capture succeeded: ${outputFileResults.savedUri}"
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                Log.d("CameraXApp", msg)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraXApp", "Photo capture failed: ${exception.message}", exception)
            }
        })
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
fun FooterCameraController(
    modifier: Modifier = Modifier.fillMaxWidth(),
    cameraOpenType: Tabs,
    onClickEffect: () -> Unit,
    onClickOpenFile: () -> Unit,
    onClickImageCapture: () -> Unit,
    onClickVideoCapture: () -> Unit,
    isEnabledLayout: Boolean = false
) {
    val coroutineScope = rememberCoroutineScope()
    val backgroundShapeWidth = 62.dp
    val itemHorizontalPadding = 26.dp
    val screenHalfWidth = LocalConfiguration.current.screenWidthDp.div(2).dp
    val horizontalContentPadding = screenHalfWidth.minus(itemHorizontalPadding.div(2))
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = when (cameraOpenType) {
            Tabs.CAMERA -> 2
            Tabs.STORY -> 1
            else -> 0
        }
    )

    var isRecord by remember { mutableStateOf(false) }

    val layoutInfo = rememberLazyListSnapperLayoutInfo(
        lazyListState = lazyListState,
        endContentPadding = screenHalfWidth.minus(30.dp),
        snapOffsetForItem = SnapOffsets.Center
    )
    val captureOptions = remember {
        when (cameraOpenType) {
            Tabs.CAMERA -> CameraCaptureOptions.values().toMutableList().apply {
                removeAll(listOf(CameraCaptureOptions.TEXT, CameraCaptureOptions.VIDEO, CameraCaptureOptions.PHOTO))
            }
            Tabs.STORY -> CameraCaptureOptions.values().toMutableList().apply {
                removeAll(
                    listOf(CameraCaptureOptions.SIXTY_SECOND, CameraCaptureOptions.FIFTEEN_SECOND, CameraCaptureOptions.VIDEO)
                )
            }
            else -> emptyList()
        }
    }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .alpha(alphaForInteractiveView(isEnabledLayout))
                    .size(width = backgroundShapeWidth, height = 22.dp)
                    .background(color = White, shape = RoundedCornerShape(16.dp))
            )
            LazyRow(
                modifier = Modifier.alpha(alphaForInteractiveView(isEnabledLayout)),
                horizontalArrangement = Arrangement.spacedBy(26.dp),
                state = lazyListState,
                userScrollEnabled = isEnabledLayout,
                contentPadding = PaddingValues(horizontal = horizontalContentPadding),
                flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
            ) {
                itemsIndexed(captureOptions) { index, it ->
                    val isCurrentItem = layoutInfo.currentItem?.index == index
                    if (isCurrentItem) {
                        isRecord = it == CameraCaptureOptions.SIXTY_SECOND || it == CameraCaptureOptions.FIFTEEN_SECOND || it == CameraCaptureOptions.VIDEO
                    }
                    Text(
                        text = it.value,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.clickable {
                            if (isEnabledLayout) {
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(index)
                                }
                            }
                        },
                        color = if (isCurrentItem) Color.Black else Color.White
                    )
                }
            }
        }

        MediumSpace()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable {
                    if (isEnabledLayout) {
                        onClickEffect()
                    }
                }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_post),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(alphaForInteractiveView(isEnabledLayout))
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable{onClickEffect()},
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = stringResource(id = R.string.effects),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.alpha(alphaForInteractiveView(isEnabledLayout))
                )
            }
            val captureButtonColor =
                when (captureOptions.getOrNull(layoutInfo.currentItem?.index ?: -1)) {
                    CameraCaptureOptions.FIFTEEN_SECOND, CameraCaptureOptions.SIXTY_SECOND, CameraCaptureOptions.VIDEO -> MaterialTheme.colorScheme.primary
                    else -> White
                }
            CaptureButton(
                modifier = Modifier.alpha(alphaForInteractiveView(isEnabledLayout)),
                color = captureButtonColor,
                onClickCapture = if (isRecord) onClickVideoCapture else onClickImageCapture
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable { onClickOpenFile() }) {
                Image(
                    painter = painterResource(id = R.drawable.img_upload_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(id = R.string.upload),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        MediumSpace()
    }
}


@Composable
fun CameraSideControllerSection(
    modifier: Modifier,
    defaultCameraFacing: CameraSelector,
    onClickController: (CameraController) -> Unit
) {
    val controllers =
        if (defaultCameraFacing == CameraSelector.DEFAULT_BACK_CAMERA) CameraController.values()
            .toMutableList().apply { remove(CameraController.MIRROR) }
        else CameraController.values().toMutableList().apply { remove(CameraController.FLASH) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(14.dp)) {
        controllers.forEach {
            ControllerItem(it, onClickController)
        }
    }
}

@Composable
fun ControllerItem(
    cameraController: CameraController, onClickController: (CameraController) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(
        4.dp, alignment = Alignment.CenterVertically
    ), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {
        onClickController(cameraController)
    }) {
        Icon(
            painterResource(id = cameraController.icon),
            contentDescription = null,
            modifier = Modifier.size(27.dp),
            tint = Color.White.copy(alpha = 0.8f)
        )
        Text(
            text = stringResource(id = cameraController.title),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

val pickVisualMediaRequest by lazy {
    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
}

fun alphaForInteractiveView(isEnabledLayout: Boolean): Float = if (isEnabledLayout) 1f else 0.28f