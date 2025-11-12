package com.example.healplus.feature.shop.collections.widgets
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.model.products.ProductsModel
import com.example.healplus.R
import com.example.healplus.feature.utils.constants.TSizes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductItemCollections(items: ProductsModel) {
    Column (modifier = Modifier.fillMaxSize()
        .padding(TSizes.XS)){
        AsyncImage(
            model = items.urls.firstOrNull(),
            contentDescription = null,
            modifier = Modifier
                .height(190.dp)
                .clip(RoundedCornerShape(TSizes.DEFAULT_SPACE/2))
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = items.name,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                )
                Text(
                    text = items.rating.toString(),
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }
            Text(
                text = NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(items.unitNames.firstOrNull()?.price!!).toString(),
                color = colorResource(R.color.purple_200),
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}