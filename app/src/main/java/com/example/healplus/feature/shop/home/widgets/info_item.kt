package com.example.healplus.feature.shop.home.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.*
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
import com.example.healplus.R

@Composable
fun DrugStoreInfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                        icon = painterResource(com.example.healplus.R.drawable.genuine),
                        title = "Thuốc chính hãng",
                        subtitle = "đa dạng và chuyên sâu"
                    )
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(com.example.healplus.R.drawable.return_of_investment),
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
                        icon = painterResource(com.example.healplus.R.drawable.tag),
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Thông tin cửa hàng",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "© 2007 - 2024 Công ty Cổ Phần Dược Phẩm HealPlus",
                )

                Text(
                    text = "Số ĐKKD 0315275368 cấp ngày 17/09/2025 tại Sở Kế hoạch Đầu tư THHN",
                )

                HorizontalDivider()

                ContactInfoItem(
                    icon = Icons.Rounded.LocationOn,
                    content = "379-381 Xuân Thủy, P. Xuân Thủy, Q.Cầu Giấy, TP. HN"
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
                .padding(vertical = 16.dp),
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
                    .size(80.dp)
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
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(32.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}