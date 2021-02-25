package com.example.myapplication.domain.utils


sealed class Screen(
    val route: String,
    val label: String,
    //var icon: ImageVector
){

    object Search : Screen("search", "Search")
    object Planner : Screen("planner", "Planner")
    object Add : Screen("add", "Add")
    object Progress : Screen("progress", "Progress")
    object Menu : Screen("menu", "Menu")
}

