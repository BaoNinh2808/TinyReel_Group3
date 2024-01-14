package com.example.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composable.TopBar
import com.example.theme.R
import com.example.theme.Typography
import com.example.theme.WhiteLightDimBg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController){
    var text by remember{mutableStateOf("")}
    var active by remember{mutableStateOf(false)}

    val scrollState = rememberScrollState()
    var oldSearchQuery = remember{
        mutableStateListOf(
            ""
        )
    }

    val hotSearchQuery = remember{
        mutableStateListOf(
            "tết ở làng địa ngục",
            "mình cùng nhau đón giáng sinh",
            "chị thảo da ua"
        )
    }

    //create a search screen
    Scaffold (
        topBar = {
            //create a top bar
            TopBar(title = stringResource(id = R.string.search)){ navController.navigateUp() }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            //create a search bar
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    oldSearchQuery.add(text)
                    active = false
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.search))
                },
                leadingIcon = {
                    //create a leading icon
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    //create a trailing icon
                    if (active){
                        Icon(modifier = Modifier.clickable{
                            if (text.isNotEmpty()){
                                text = ""
                            } else {
                                active = false
                            }
                        },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                        )
                    }
                },
                colors = SearchBarDefaults.colors(
                    containerColor = WhiteLightDimBg,
                    dividerColor = WhiteLightDimBg,
                )
            ) {
                // create 2 list of search query: old search query and hot search query (from api)
                // create a row for each search query
                oldSearchQuery.forEach {
                    // pass the first item of old search query to search bar
                    if (it == oldSearchQuery[0]){
                        // don't do anything
                    }
                    else{
                        Row(modifier = Modifier.padding(all = 16.dp)) {
                            Icon(
                                modifier = Modifier.padding(end = 10.dp),
                                imageVector = Icons.Default.History,
                                contentDescription = "History Icon")
                            Text(text = it)
                        }
                    }
                }

                //Text for represent hot search query

                Text(
                    modifier = Modifier.padding(all = 16.dp),
                    text = stringResource(id = R.string.hot_search_query),
                    style = Typography.displayMedium
                )

                hotSearchQuery.forEach {
                    Row(modifier = Modifier.padding(all = 16.dp)) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Default.Whatshot,
                            contentDescription = "History Icon")
                        Text(text = it)
                    }
                }
            }
            // create 2 list of search query: old search query and hot search query (from api)
            // create a row for each search query
            oldSearchQuery.forEach {
                // pass the first item of old search query to search bar
                if (it == oldSearchQuery[0]){
                    // don't do anything
                }
                else{
                    Row(modifier = Modifier.padding(all = 16.dp)) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Default.History,
                            contentDescription = "History Icon")
                        Text(text = it)
                    }
                }
            }

            //Text for represent hot search query

            Text(
                modifier = Modifier.padding(all = 16.dp),
                text = stringResource(id = R.string.hot_search_query),
                style = Typography.displayMedium
            )

            hotSearchQuery.forEach {
                Row(modifier = Modifier.padding(all = 16.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.Whatshot,
                        contentDescription = "History Icon")
                    Text(text = it)
                }
            }
        }
    }
}

//preview
@Composable
@Preview
fun SearchScreenPreview(){
    SearchScreen(navController = rememberNavController())
}


