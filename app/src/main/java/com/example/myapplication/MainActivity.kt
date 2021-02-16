package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.selects.select
import android.content.Context as ContentContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MyApp()
            }
        }

    }
}



@Composable
fun MyApp(){

            Column(
                modifier = Modifier

                    .background(color = Color(0XFF13354F))
                    .fillMaxSize(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BalanceInfo()
                BottomBar()
            }


}


@Composable
fun BalanceInfo(){
    Column(modifier = Modifier
        .padding(bottom = 16.dp)) {
        Text(
            text = "Current Balance",
            color = Color(0XFFF8F8F8),
            fontSize = 12.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 30.dp)
        )
        Text(
            text = "$34,44.00",
            color= Color(0XFFF8F8F8),
            fontSize = 35.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = "+$120 (13.89%)",
            color = Color(0XFF00FFD4),
            fontSize = 12.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}



@Composable
fun BottomBar(){
    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Add, Screen.Profile)
    val shape = RoundedCornerShape(topLeft = 50f, topRight = 50f, bottomLeft = 50f, bottomRight = 50f)


               Scaffold(
                   backgroundColor = Color(0xff13354F),

                   bottomBar = {
                       BottomNavigation(
                           backgroundColor = Color(0xff102B41),
                           contentColor = Color.Blue,

                           modifier = Modifier
                               .padding(16.dp)
                               .height(60.dp)

                       ) {
                           val navBackStackEntry by navController.currentBackStackEntryAsState()
                           val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                           val tintColor = Color(0xff6F6F6F)
                           items.forEach {
                               BottomNavigationItem(
                                   modifier = Modifier.width(128.dp).height(128.dp),

                                   icon = {
                                       when(it){
                                           Screen.Home -> Icon(vectorResource(R.drawable.chart_24),"", tint = tintColor)
                                           Screen.Add -> Icon(vectorResource(R.drawable.add_box_24), "", tint = tintColor)
                                           Screen.Profile -> Icon(vectorResource(R.drawable.account_box_24), "", tint =  tintColor)
                                       }
                                   },

                                   selected = currentRoute == it.route,
                                   onClick = {
                                       if (currentRoute != it.route){
                                           navController.navigate(it.route)
                                       }
                                       {

                                       }
                                   }
                               )

                           }
                       }
                   }
               ) {
                   ScreenController(navController)
               }

}

@Composable
fun ScreenController(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){ HomeScreen() }
        composable(Screen.Add.route){AddScreen() }
        composable(Screen.Profile.route){ AccountScreen() }
    }
}



fun showMessage(context: ContentContext, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
