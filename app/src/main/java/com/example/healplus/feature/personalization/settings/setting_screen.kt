package com.example.healplus.feature.personalization.settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.TAppBar
import com.example.healplus.feature.common.widgets.TButtonLogOut
import com.example.healplus.feature.personalization.settings.widgets.SettingsItem
import com.example.healplus.feature.personalization.settings.widgets.TDeleteAccountButton
import com.example.healplus.feature.personalization.settings.widgets.TSectionTitle

@Composable
fun SettingScreen(
    navController: NavController,
){
    val context = LocalContext.current
    Scaffold (
        topBar = {
            TAppBar(
                title = R.string.settings,
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
                 Row(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(horizontal = 16.dp),
                     horizontalArrangement = Arrangement.SpaceBetween,
                     verticalAlignment = Alignment.CenterVertically
                 ) {
                     Text(
                         text = "Thống kê doanh thu",
                         fontSize = 16.sp,
                         fontWeight = FontWeight.Bold
                     )
                     Text(
                         text = "Xem tất cả",
                         fontSize = 14.sp,
                         color = Color(0xFF007BFF),
                         modifier = Modifier
                             .clickable { navController.navigate("oderscreen") }
                     )
                 }
             }
             item { TSectionTitle(title = R.string.personal) }
             item { SettingsItem(title = R.string.profile){
                 navController.navigate("profile")
                }
             }
             item { SettingsItem(title = R.string.address) }
             item { SettingsItem(title = R.string.Paysmethoot) }

             
             item { TSectionTitle(title = R.string.shop) }
             item { SettingsItem(title = R.string.country, value = "Vietnam") }
             item { SettingsItem(title = R.string.terms) }

             
             item { TSectionTitle(title = R.string.account) }
             item { SettingsItem(title = R.string.lauguage, value = "English") }
             item { SettingsItem(title = R.string.about) }

             
             item { TDeleteAccountButton(
                 title = R.string.delete_account,
                 onClick = {
                     navController.navigate("deleteAccount")
                 }
             ) }
             item {
                 TButtonLogOut(
                     title = R.string.logout,
                     onClick = {
//                         authViewModel.signOut()
//                         context.startActivity(Intent(context, LoginActivity::class.java))
                     }
                 )
             }

         }
    }
}

