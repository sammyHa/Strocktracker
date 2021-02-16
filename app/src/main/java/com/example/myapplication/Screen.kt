package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.isMouseInput
import androidx.compose.ui.res.vectorResource


sealed class Screen(
    val route: String,
    val label: String,
    //var icon: ImageVector
){

    object Home : Screen("HomeFragment", "Home")
    object Add : Screen("Add", "Add")
    object Profile : Screen("Profile", "Account")
}

