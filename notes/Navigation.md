# Navigation

## Sơ lược
Một số khái niệm cơ bản:
- **Navigation Destination**: một màn hình hoặc một view trong ứng dụng.
- **Navigation Action**: là liên kết giữa hai **Navigation Destination** (ví dụ: một sự kiện nào đó (và các thông tin của nó) được truyền từ một màn hình này sang màn hình khác).
- **Navigation Graph**: Đồ thị mô tả các **Navigation Destination** và sự kết nối giữa chúng.
- **Navigation Controller**: là thành phần *thật sự* quản lí việc điều hướng giửa các destination.
- **Navigation Host**: là thành phần biết về tất cả các destination và action trong Navigation Graph. Nó xử lý thực hiện điều hướng trong các destimation khác nhau. **NavHost = NavGraph + NavCotroller**.

## AppNavHost và navController
Trong ứng dụng của mình, **Navigation Host** nằm ở `app/src/main/java/com/puskal/tiktokcompose/navigation/AppNavHost.kt`
 ```kotlin
 @Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_SCREEN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeNavGraph(navController)
        commentListingNavGraph(navController)
        creatorProfileNavGraph(navController)
        inboxNavGraph(navController)
        authenticationNavGraph(navController)
        loginEmailPhoneNavGraph(navController)
        friendsNavGraph(navController)
        myProfileNavGraph(navController)
        settingNavGraph(navController)
        cameraMediaNavGraph(navController)
    }
}
```

 `navCotroller`: Navigation Controller được truyền vào `AppNavHost` và cũng được truyền đi cho các destination khác. `navController` được khởi tạo ở `RootScreen`:
 ```kotlin
 val navController = rememberNavController(bottomSheetNavigator)
```

## Navigation Graph của được xây dựng thế nào?
Sử dụng `androidx.navigation.NavGraphBuilder`:
- Đầu tiên, ở mỗi **feature** có một Navigation Graph riêng. Ví dụ,`myprofile`:
    ```kotlin
    fun NavGraphBuilder.myProfileNavGraph(navController: NavController) {
        composable(route = DestinationRoute.MY_PROFILE_ROUTE) {
            MyProfileScreen(navController)
        }
    }
    ```
    Lúc này, sẽ có một graph là `myProfileNavGraph`, chứa một destination là `MyProfileScreen` (được bind bởi `composable(route = DestinationRoute.MY_PROFILE_ROUTE) { MyProfileScre(navController) }`).
    `DestinationRoute.MY_PROFILE_ROUTE` là binding với một chuỗi string để các route là unique (mấy chuỗi này đặt sao cũng được, miễn đừng trùng nhau).
- Sau đó, ở `AppNavHost`, ta include graph con này vào:
    ```kotlin
    @Composable
    fun AppNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        startDestination: String = HOME_SCREEN_ROUTE
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            // ...
            myProfileNavGraph(navController)
            // ...
        }
    }
    ```