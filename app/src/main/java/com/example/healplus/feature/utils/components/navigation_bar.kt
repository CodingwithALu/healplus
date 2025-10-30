import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.core.model.bottomapp.NavItemModel

@Composable
fun TNavigationBar(
    navItemList: List<NavItemModel.DrawableResItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    BadgedBox(badge = {
                        if (navItem.badgeCount > 0)
                            Badge { Text(text = navItem.badgeCount.toString()) }
                    }) {
                        Icon(
                            painter = painterResource(id = navItem.iconResId),
                            contentDescription = navItem.label
                        )
                    }
                },
                label = { Text(text = navItem.label) }
            )
        }
    }
}