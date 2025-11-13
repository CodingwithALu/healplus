package com.example.healplus.feature.shop.home.widgets
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.users.UserModel
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.images.TCircularImage
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun TAppbarHome(
    user: UserModel,
    onAvatarClick: () -> Unit = {},
    searchClick: () -> Unit = {},
    notify: () -> Unit = {},
    showNotification: Boolean = false,
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = TSizes.DEFAULT_SPACE/4)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                TCircularImage(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    drawableResId = R.drawable.sammy_line_man_receives_a_mail,
                    onClick = onAvatarClick,
                    fit = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(TSizes.XS))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.spot.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 12.sp
                    )
                    Image(
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                            .clickable { },
                        painter = painterResource(id = R.drawable.icon_point),
                        contentDescription = "Coin",
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(30.dp)
                        .width(30.dp)
                        .clickable { searchClick() },
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Bell",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(12.dp))
                if (showNotification) {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(30.dp)
                            .width(30.dp)
                            .clickable { notify() },
                        painter = painterResource(id = R.drawable.bell_icon),
                        contentDescription = "Bell",
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}
