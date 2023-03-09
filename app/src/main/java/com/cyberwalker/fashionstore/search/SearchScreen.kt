package com.cyberwalker.fashionstore.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.data.ShopItem
import com.cyberwalker.fashionstore.ui.theme.*

@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null
        )

        Scaffold(
            scaffoldState = scaffoldState,
//            bottomBar = {
//                BottomNav(navController = navController)
//            },
            backgroundColor = Color.Transparent
        ) { innerPadding ->
            SearchScreenContent(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun SearchScreenContent(
    modifier: Modifier,
    // onAction: (actions: LoginScreenActions) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            text = "Search",
            style = MaterialTheme.typography.large.copy(
                shadow = Shadow(
                    color = dark,
                    offset = Offset(0.0f, 4.0f),
                    blurRadius = 5f
                )
            )
        )
        SearchBar()
        ResultItems()
    }

}

@Composable
private fun SearchBar(
    viewModel: SearchViewModel = hiltViewModel()
) {

    val search = remember { mutableStateOf(TextFieldValue()) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = search.value,
            onValueChange = { search.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            leadingIcon = {
                IconButton(onClick = { viewModel.findItems(search.value.text) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = highlight,
                focusedLabelColor = dark,
                cursorColor = highlight
            ),
            placeholder = { Text("Search") }
        )
    }
}

@Composable
fun ResultItems(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val tmp = viewModel.searchResult.collectAsState()
    val resultList = rememberSaveable() { tmp.value }
    Log.d("ResultItems", "findItems: ${resultList}")
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(resultList) { index, user ->
            Item(shopItem = user)
        }
    }
}

@Composable
fun Item(
    shopItem: ShopItem
) {
    Box(
        Modifier
            .size(240.dp, 300.dp)
            .background(color = cardColorYellow, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(16.dp))
        //.clickable { onAction(HomeScreenActions.Details) }
    ) {
        Image(
            modifier = Modifier
                .size(200.dp, 288.dp)
                .align(Alignment.BottomCenter),
            painter = painterResource(id = shopItem.image!!),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp, top = 8.dp)
                .clickable { }
        )
        Row (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(color = Color.White)
        ) {
            Text(
                text = shopItem.name!!,
                style = MaterialTheme.typography.medium_18_bold
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = shopItem.price!!,
                style = MaterialTheme.typography.medium_18_bold
            )
        }
    }
    //Spacer(modifier = Modifier.size(8.dp))

    Spacer(modifier = Modifier.size(24.dp))
}
