//package com.example.core_utils.common.widgets
//
//import android.content.Context
//import android.net.Uri
//import android.util.Log
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.platform.LocalContext
//import com.example.healplus.feature.shop.managers.uploadImageToServer
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//@Composable
//fun rememberImagePickerLauncher(
//    context: Context = LocalContext.current,
//    coroutineScope: CoroutineScope = rememberCoroutineScope(),
//    onImageUploaded: (String) -> Unit
//): ActivityResultLauncher<String> {
//    return rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            coroutineScope.launch {
//                val url = uploadImageToServer(uri, context)
//                if (url != null) onImageUploaded(url)
//            }
//        } else {
//            Log.d("ImagePicker", "No image selected (cancelled).")
//        }
//    }
//}