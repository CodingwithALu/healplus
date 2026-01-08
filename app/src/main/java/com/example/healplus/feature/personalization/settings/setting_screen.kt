package com.example.healplus.feature.personalization.settings
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.AuthViewModel1
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.TAppBar
import com.example.healplus.feature.common.widgets.TButtonLogOut
import com.example.healplus.feature.personalization.settings.widgets.TSectionTitle
import com.example.healplus.feature.utils.route.Screen


@Composable
fun SettingScreen(
    navController: NavController,
){
    val viewModel : AuthViewModel1 = hiltViewModel()
    val items = listOf(
        SettingItem("Quét mã QR", Icons.Default.QrCodeScanner) {},
        SettingItem("Thông tin về Healplus", Icons.Default.Info) {},
        SettingItem("Chính sách & bảo mật", Icons.Default.Security) {},
        SettingItem("Câu hỏi thường gặp", Icons.Default.Help) {},
        SettingItem("Tổng đài chăm sóc", Icons.Default.Call) {}
    )
    val me = listOf(
        SettingItem("Đơn hàng của tôi", Icons.Default.CardTravel) {
            navController.navigate("oderscreen")
        },
        SettingItem("Thông tin cá nhân", Icons.Default.Info) {
            navController.navigate("profile")
        },
        SettingItem("Địa chỉ giao hàng", Icons.Default.LocationOn) {},
        SettingItem("Thẻ thanh toán", Icons.Default.CreditCard) {},
    )
    val account = listOf(
        SettingItem("Ngôn ngữ", Icons.Default.Language) {},
        SettingItem("Xóa tài khoản", Icons.Default.DeleteForever) {
            navController.navigate("deleteAccount")
        },
    )
    Scaffold (
        topBar = {
            TAppBar(
                title = R.string.settings,
                showBackArrow = true,
                onClick = { navController.popBackStack() },
            )
        })
     {paddingValues ->
         LazyColumn(
             modifier = Modifier.fillMaxSize()
             .padding(paddingValues), 
         contentPadding = PaddingValues(bottom = 184.dp) 
         ) {
             item {
                 Card(
                     shape = RoundedCornerShape(12.dp),
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(16.dp),
                     colors = CardDefaults.cardColors(
                         containerColor = Color.White
                     ),
                     elevation = CardDefaults.cardElevation(2.dp)
                 ) {
                     Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                         me.forEachIndexed { index, item ->
                             SettingRow(item)
                         }
                     }
                 }
             }
             item { TSectionTitle(title = R.string.personal) }
             item {
                 Card(
                     shape = RoundedCornerShape(12.dp),
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(16.dp),
                     colors = CardDefaults.cardColors(
                         containerColor = Color.White
                     ),
                     elevation = CardDefaults.cardElevation(2.dp)
                 ) {
                     Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                         items.forEachIndexed { index, item ->
                             SettingRow(item)
                         }
                     }
                 }
             }
             item { TSectionTitle(title = R.string.account) }
             item {
                 Card(
                     shape = RoundedCornerShape(12.dp),
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(16.dp),
                     colors = CardDefaults.cardColors(
                         containerColor = Color.White
                     ),
                     elevation = CardDefaults.cardElevation(2.dp)
                 ) {
                     Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                         account.forEachIndexed { index, item ->
                             SettingRow(item)
                         }
                     }
                 }
             }
             item {
                 TButtonLogOut(
                     title = R.string.logout,
                     onClick = {
                         viewModel.logout()
                         navController.navigate(Screen.Login.route){
                             popUpTo (0){
                                 inclusive = true
                             }
                             launchSingleTop = true
                         }
                         Log.d("Logout","Success")
                     }
                 )
             }
         }
    }
}
data class SettingItem(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)
@Composable
fun SettingRow(
    item: SettingItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = item.title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

