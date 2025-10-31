package com.example.healplus.acitivity

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    /*
     @HiltAndroidApp:
     - Khởi tạo Hilt cho toàn ứng dụng; tạo Application-level component.
     - Bắt buộc nếu bạn dùng @Inject trong Activity/ViewModel/Module để Hilt hoạt động.
     - Sau khi thêm, nhớ cập nhật AndroidManifest.xml nếu cần (application name).
    */
    override fun onCreate(){
        super.onCreate()
    }
}