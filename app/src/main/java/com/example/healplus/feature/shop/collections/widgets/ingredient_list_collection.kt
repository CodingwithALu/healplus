import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.core.model.ingredients.IngredientsModel
import com.example.healplus.feature.utils.constants.TSizes
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun IngredientListCollection(
    ingredients: List<IngredientsModel>,
    event: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TSizes.DEFAULT_SPACE / 4)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        mainAxisSpacing = 4.dp,
        crossAxisSpacing = 4.dp
    ) {
        ingredients.forEach { ingredient ->
            Box(
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp.dp / 2) - 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(
                        onClick = {
                            // compute a safe id with fallback if idIngredient is blank
                            val computedId =
                                ingredient.iding.takeIf { it.isNotBlank() }
                                    ?: (ingredient.iding.takeIf { it.isNotBlank() }
                                        ?: ingredient.title)
                            event(computedId)
                        }
                    )
            ) {
                IngredientItemCollection(ingredient)
            }
        }
    }
}
