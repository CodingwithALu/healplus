import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.core.model.elements.ElementsModel
import com.example.healplus.feature.shop.collections.widgets.ElementItemCollection
import com.example.healplus.feature.utils.constants.TSizes
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ElementList(
    element: List<ElementsModel>,
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
        element.forEach { elementItem ->
            Box(
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp.dp / 2) - 8.dp)
                    .clickable(
                        onClick = {
                            val computedId =
                                elementItem.ide.takeIf { it.isNotBlank() }
                                    ?: (elementItem.ide.takeIf { it.isNotBlank() }
                                        ?: elementItem.title)
                            event(computedId)
                        }
                    )
            ) {
                ElementItemCollection(elementItem)
            }
        }
    }
}