
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun TermsAndConditionCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isDark: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = if (isDark) Color.White else MaterialTheme.colorScheme.primary
    Row(modifier = modifier, verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange(!checked) },
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(12.dp))

        // Compose annotated text
        val termsText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                append("I agree to ")
            }
            pushStringAnnotation(tag = "privacy", annotation = "privacy_policy")
            withStyle(
                style = SpanStyle(
                    color = textColor,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Privacy Policy")
            }
            pop()
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                append(" and ")
            }
            pushStringAnnotation(tag = "terms", annotation = "terms_of_use")
            withStyle(
                style = SpanStyle(
                    color = textColor,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Terms of Use")
            }
            pop()
        }

        Text(
            text = termsText,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCheckedChange(!checked) } // Optional: Click làm chọn checkbox
        )
    }
}