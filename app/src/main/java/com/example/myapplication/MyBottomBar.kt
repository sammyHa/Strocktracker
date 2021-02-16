package com.example.myapplication

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource

@Composable
fun BottomMyBAr(){
    BottomNavigation() {
        BottomNavigationItem(
            icon = {
                Icon(vectorResource(id = R.drawable.add_a_photo_24),"",
                    tint = Color.Blue) //this is tint
            },
            selected = true, onClick = {

            })
    }
}
