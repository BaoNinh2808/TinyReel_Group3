# Kotlin Coroutines và Flow

## Kotlin Coroutines
Có thể hiểu nôm na "**Coroutines**" giống với threads, nhưng thay vì được quản lí bởi OS thì được quản lí bởi User.
Về mặt lập trình, thì **Coroutines** cho phép viết mã không đồng bộ theo cách đồng bộ.
> One can think of a coroutine as a light-weight thread. Like threads, coroutines can run in parallel, wait for each other and communicate. The biggest difference is that coroutines are very cheap, almost free: we can create thousands of them, and pay very little in terms of performance. True threads, on the other hand, are expensive to start and keep around. A thousand threads can be a serious challenge for a modern machine.

## Flow
