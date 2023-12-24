package com.example.tinyreel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.navigation.compose.rememberNavController


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.home.HomeScreen
import com.example.profile.ProfileScreen

@Composable
fun RootScreen(){
    val navController = rememberNavController()

    //Vd ve 1 man hinh
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
//            Text(text = "AAAAAA")
//            Text(text = "BBBBBB")
            ProfileScreen(navController)

        }

//    HomeScreen()
}