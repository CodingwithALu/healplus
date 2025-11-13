package com.example.healplus.feature.shop.review

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.model.products.conten.ReviewItem
import com.example.core.viewmodel.ReviewViewModel
import com.example.healplus.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteReviewScreen(
    navController: NavController,
    productId: String
) {
    val viewModel: ReviewViewModel = hiltViewModel()
    val uid = viewModel.uid.collectAsState()
    var rating by remember { mutableIntStateOf(0) }
    var reviewComment by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.write_your_review_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = stringResource(R.string.back_button_desc)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.how_would_you_rate_product),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            RatingInput(
                currentRating = rating,
                onRatingChange = { newRating -> rating = newRating },
                modifier = Modifier.padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = reviewComment,
                onValueChange = { reviewComment = it },
                label = { Text(stringResource(R.string.your_comment_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp),
                placeholder = { Text(stringResource(R.string.share_your_thoughts_placeholder)) }
            )
            Text(
                text = stringResource(
                    R.string.min_characters_required,
                    20
                ),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (rating == 0) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.please_select_rating_toast),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }
                    coroutineScope.launch {
                        val sdf = SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        )
                        val currentDateAndTime = sdf.format(Date())
                        val review = ReviewItem(
                            idp = productId,
                            rating = rating.toFloat(),
                            comment = reviewComment,
                            date = currentDateAndTime,
                            idUser = uid.value
                        )
                        viewModel.createReview(review)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (true) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(stringResource(R.string.submit_review_button))
                }
            }
        }
    }
}

@Composable
fun RatingInput(
    currentRating: Int,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    starSize: Dp = 40.dp,
    selectedColor: Color = Color(0xFFFFC107),
    defaultColor: Color = Color.Gray
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..starCount) {
            Icon(
                painter = if (i <= currentRating) painterResource(R.drawable.star) else painterResource(
                    R.drawable.star_24px
                ),
                contentDescription = stringResource(R.string.rate_star_desc, i),
                tint = if (i <= currentRating) selectedColor else defaultColor,
                modifier = Modifier
                    .size(starSize)
                    .padding(horizontal = 4.dp)
                    .clickable { onRatingChange(i) }
            )
        }
    }
}