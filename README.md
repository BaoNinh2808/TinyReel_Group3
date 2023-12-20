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