# Module `core`

## Overview
Cấu trúc của `core`:
- `base/`
    - `BaseViewModel.kt`
- `extension/`
    - `Extension.kt`
    - `Space.kt`
- `utils/`
- `AppContract.kt`
- `DestinationRoute.kt`

## `base/BaseViewModel.kt`
`BaseViewModel` là Base class cho các view model được sử dụng trong ứng dụng.
```kotlin
abstract class BaseViewModel<ViewState, Event> : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState?>(null)
    val viewState = _viewState.asStateFlow()

    fun updateState(viewState:ViewState){
        _viewState.value=viewState
    }
    abstract fun onTriggerEvent(event:Event)

}
```
- Type parameter: `ViewState` và `Event`
- Thuộc tính `_viewState`: được dùng để lưu trạng thái của view (bất đồng bộ).
- Thuộc tính `viewState`: cho phép bên ngoài đọc các thay đổi từ `_viewState` mà không cho chỉnh sửa.
- `fun updateState`: cập nhật lại `_viewState`.
- `abstract fun onTriggerEvent`: abstract fun cho một event.

## `extension/Extension.kt`
- `formattedCount`: convert số thập phân sang dạng order of magnitude.
- `randomUploadDate`: để fake data.
- `getFormattedInternationalNumber`: không thấy dùng ở đâu hết.
- `Context.getCurrentBrightness`: lấy độ sáng màn hình của thiết bị (dùng trong màn hình camera).

## `extension/Space.kt`
Định nghĩa các khoảng vertical space dùng trong Compose.

## `utils/DisableRippleInteractionSource.kt`
Tắt ripple effect (được dùng trong màn hình camera).

## `utils/FileUtils.kt`
Gồm 1 hàm là `extractThumbnail`, có nhiệm vụ lấy thumbnail của một video.

## `utils/IntentUtils.kt`
- Intent: message giữa các activity/component.
- File này cài đặt một số Intent được sử dụng trong ứng dụng.
    - `Context.share`: Được dùng khi share một video.
    ```kotlin
    // TiktokVerticalVideoPager.kt
    Icon(
        painter = painterResource(id = R.drawable.ic_share),
        contentDescription = null,
        tint = Color.Unspecified,
        modifier = Modifier
            .size(32.dp)
            .clickable {
                onClickShare?.let { onClickShare.invoke() } ?: run {
                    context.share(
                        text = "https://github.com/puskal-khadka"
                    )
                }
            }
    )
    ```
    - `Context.redirectToApp`: dùng trong màn hình profile. Khi người dùng nhấn vào đường dẫn tới tài khoản mạng xã hội, thì request view (mở) ứng dụng đó lên.
    ```kotlin
    // CreatorProfileScreen.kt
    creatorProfile?.pinSocialMedia?.let {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(40.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(2.dp),
                    color = Gray.copy(alpha = 0.2f)
                )
                .clickable {
                    context.redirectToApp(
                        link = it.link,
                        type = when (it.type) {
                            SocialMediaType.INSTAGRAM -> INSTAGRAM
                            SocialMediaType.YOUTUBE -> YOUTUBE
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            val icon = when (it.type) {
                SocialMediaType.INSTAGRAM -> R.drawable.ic_instagram
                SocialMediaType.YOUTUBE -> R.drawable.ic_youtube
            }
            Icon(painter = painterResource(id = icon), contentDescription = null)
        }
    }
    ```
    - `context.openAppSetting`: mở phần **Cài đặt** của thiết bị (dùng trong cấp quyền cho camera).
    ```kotlin
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
    ```

## `AppContract.kt`
Định nghĩa một số constant:
```kotlin
object AppContract {
    object Type {
        const val YOUTUBE = "type_youtube"
        const val INSTAGRAM = "type_instagram"
    }

    object Annotate {
        const val ANNOTATED_TAG = "annotated_tag"
        const val ANNOTATED_TERMS_OF_SERVICE = "annotated-terms_of_service"
        const val ANNOTATED_PRIVACY_POLICY = "annotated_privacy_policy"
        const val ANNOTATED_LEARN_MORE = "learn_more"
    }
}
```
- `Type`: các mạng xã hội.
- `Annotate`: một số constant dùng trong xây dựng annotated string ở màn hình authentication.

## `DestinationRoute.kt`
[Navigation](Navigation.md)