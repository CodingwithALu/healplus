package com.example.healplus.feature.shop.review

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.model.products.conten.ReviewItem
import com.example.healplus.R
import com.example.healplus.feature.shop.product.ReviewCard
import com.example.healplus.feature.shop.product.ReviewSummary

@Composable
fun NewReviewsSection(
    averageRating: Float,
    totalReviews: Int,
    individualReviews: List<ReviewItem>,
    onSeeAllReviewsClick: () -> Unit,
    onWriteReviewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding()) {
        Text(
            text = stringResource(R.string.product_reviews_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReviewSummary(
            averageRating = averageRating,
            totalReviews = totalReviews,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (individualReviews.isNotEmpty()) {
            Text(
                text = stringResource(R.string.notable_reviews),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            individualReviews.take(3).forEach { review ->
                ReviewCard(
                    review = review,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
            if (totalReviews > 3) {
                TextButton(
                    onClick = { onSeeAllReviewsClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        stringResource(
                            R.string.see_all_reviews,
                            totalReviews
                        )
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        } else {
            Text(
                text = stringResource(R.string.no_reviews_yet),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {
                onWriteReviewClick()
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.write_a_review))
        }
    }
}