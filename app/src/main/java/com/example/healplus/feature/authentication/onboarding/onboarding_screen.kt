package com.example.healplus.feature.authentication.onboarding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healplus.R

@Composable
fun OnboardingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val semantics = Modifier.semantics {
                        contentDescription = "Shopping Bag"
                        role = Role.Image
                    }
            Layout(
                Modifier
                            .width(200.dp)
                            .height(246.dp)
                    .then(semantics)
                    .clipToBounds()
                    .paint(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentScale = ContentScale.Fit
                    )
            ) { _, constraints ->
                layout(constraints.minWidth, constraints.minHeight) {}
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = stringResource(R.string.intro_app_welcom),
                fontSize = 19.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 33.sp
            )
        }

        Spacer(modifier = Modifier.height(106.dp))

        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
            ,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = stringResource(id = R.string.intro_next),
                fontSize = 22.sp)
        }
        Row (
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = stringResource(id = R.string.have_an_account))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        navController.navigate("signup")
                    }
            )
        }
    }
}