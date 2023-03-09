package com.cyberwalker.fashionstore.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.ui.theme.dark
import com.cyberwalker.fashionstore.ui.theme.large

@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController
    // onAction: (actions: ) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
//        bottomBar = {
//            BottomNav(navController = navController)
//        }
    ) { innerPadding ->
        ProfileScreenContent(modifier = Modifier.padding(innerPadding)/*, onAction = onAction*/)
    }
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    // onAction: (actions: LoginScreenActions) -> Unit
) {
    Top()
}

@Composable
fun Top() {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Profile",
            style = MaterialTheme.typography.large.copy(
                shadow = Shadow(
                    color = dark,
                    offset = Offset(0.0f, 4.0f),
                    blurRadius = 5f
                )
            ),
        )

        Image(
            modifier = Modifier
                .defaultMinSize(minWidth = 293.dp, minHeight = 387.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.ic_indicator),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentDescription = null
        )
    }
}

