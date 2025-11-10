package com.example.healplus.feature.shop.home.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healplus.R
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun StoreInfoScreenWidgets(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TSizes.DEFAULT_SPACE/2),
        verticalArrangement = Arrangement.spacedBy(TSizes.DEFAULT_SPACE/2)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(R.drawable.genuine),
                        title = "Thuốc chính hãng",
                        subtitle = "đa dạng và chuyên sâu"
                    )
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(R.drawable.return_of_investment),
                        title = "Đổi trả trong 30 ngày",
                        subtitle = "kể từ ngày mua hàng"
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(R.drawable.tag),
                        title = "Cam kết 100%",
                        subtitle = "chất lượng sản phẩm"
                    )
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(com.example.healplus.R.drawable.free_shipping),
                        title = "Miễn phí vận chuyển",
                        subtitle = "theo chính sách giao hàng"
                    )
                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = TSizes.DEFAULT_SPACE/2),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Thông tin cửa hàng",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "© 2007 - 2024 Công ty Cổ Phần Dược Phẩm HealPlus",
                    fontSize = 12.sp
                )
                Text(
                    text = "Số ĐKKD 0315275368 cấp ngày 17/09/2025 tại Sở Kế hoạch Đầu tư THHN",
                    fontSize = 12.sp
                )
                HorizontalDivider()
                ContactInfoItem(
                    icon = Icons.Rounded.LocationOn,
                    content = "379-381 Xuân Thủy, P. Xuân Thủy, Q.Cầu Giấy, TP. HN",
                )
                ContactInfoItem(
                    icon = Icons.Rounded.Phone,
                    content = "(028)73023456"
                )
                ContactInfoItem(
                    icon = Icons.Rounded.Email,
                    content = "sale@healplus.com.vn"
                )
                ContactInfoItem(
                    icon = Icons.Rounded.Person,
                    content = "Người quản lý nội dung: Lầu A Lử"
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val semantics =
                Modifier.semantics {
                    contentDescription = "Logo"
                    role = Role.Image
                }
            Layout(
                Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .then(semantics)
                    .clipToBounds()
                    .paint(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentScale = ContentScale.Fit
                    )
            ) { _, constraints ->
                layout(constraints.minWidth, constraints.minHeight) {}
            }

        }
    }
}
@Composable
private fun ContactInfoItem(
    icon: ImageVector,
    content: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}
@Composable
private fun InfoItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(TSizes.DEFAULT_SPACE/2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(26.dp)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                minLines = 1,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}