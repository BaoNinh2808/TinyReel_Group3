# TINYREEL - GROUP 3

## Cách một ứng dụng Android chạy

- B1: Đọc file Manifest.xml
- B2: Tìm trong tag `application` xem có chỉ định đối tượng **Application** nào không? - Sẽ chỉ định lại thông qua `android:name = ...` 
> Đối tượng **Application** là đối tượng kế thừa `Application` có nhiệm vụ quản lý toàn bộ ứng dụng.

- B3: Nếu không chỉ định lại thì dùng mặc định của Android. Nếu có thì sử dụng lớp được chỉ định này (ở trong ứng dụng của mình có chỉ định lại `android:name=".MyApp"`)

- B4: Khởi tạo đối tượng **Application** và các ràng buộc (dependency) được chỉ định cho toàn ứng dụng được khai báo trong đối tượng này 

>(ở app của mình là `@Hilt` dùng để Dependency Inject. Để dùng được thì phải implement dependency `com.google.dagger:hilt-android:xxxx` )

- B5: Tìm Activity khởi động - là Activity có nhãn `MAIN` và `LAUNCHER`. 

## Thêm các màu trong file `Theme/Color.kt`

Vì định nghĩa các màu này cần dùng tới hàm `Color()` trong gói `androidx.compose.ui.graphics.Color` nên chúng ta cần phải implement `"androidx.compose.ui:ui:xxxx"` (với xxxx là phiên bản được chọn).

- B1: Vào **file** --> **project structure** --> **dependency** 
- B2: Chọn module muốn add dependency vào (chọn module `theme`)
- B3: Nhấn dấu `+`, search `com.google.dagger:hilt-android:*` và chọn phiên bản
- B4: Nhấn **Apply**

## Thêm font vào file `Theme/Font.kt`

- B1: Tải các font cần thiết về và để trong thư mục **font** ở **res**
- B2: Tạo file Font.kt giống như trên github

## Thêm các style của chữ vào file `Theme\Type.kt`

Cần imlement `androidx.compose.material3:material3:xxxx`

## Thêm các theme vào file `Theme\theme.kt`

Thêm theme dark và light vào

## Thêm folder `extension` trong `core` và 2 file `Extension.kt` và `Space.kt`

Trong **main** của **core** tạo java folder.  
Trong java folder tạo package `com.example.core.extension`.  
Trong package tạo 2 file `Extension.kt` và `Space.kt`.

## Để các file trong module `app` dùng được cách hàm được định nghĩa ở module khác.

Cách làm: Chuyển các module khác về dạng `library` và sau đó module `app` implementation các `library` này.

- B1: Chuyển các module về dạng `library`. 
    - Nhìn chung thì `library` và `module` có cấu trúc giống hệt nhau. Ta chỉ cần sửa trong file `gradle.build.kts`
    - Sửa: 
    >       plugins {
    >           id("com.android.application")
    >       }

    Thành 

    >       plugins {
    >           id("com.android.library")
    >       }

    - Trong default config, xóa các dòng:
    >       applicationId = "com.example.profile"
    >       targetSdk = 34
    >       versionCode = 1
    >       versionName = "1.0"

    - Chỉ để lại:
    >       minSdk = 28
    >       testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

- B2: Vào **file** --> **project structure** --> **Dependencies**
- B3: Chọn module **app**
- B4: Nhấn dấu `+` và chọn `Module Dependency`
- B5: Chọn `module` muốn implementation và nhấn `apply`