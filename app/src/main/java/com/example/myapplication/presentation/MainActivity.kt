package com.example.myapplication.presentation

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapplication.views.MenuScreen
import com.example.myapplication.R
import com.example.myapplication.domain.utils.Screen
import com.example.myapplication.views.SearchScreen
import com.example.myapplication.network.RecipeService
import com.example.myapplication.views.PlannerScreen
import com.example.myapplication.views.ProgressScreen
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import android.content.Context as ContentContext



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG : String = "AppDebug"

    @Inject
    lateinit var app: Application

    @Inject
    lateinit var someString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "OnCreate: ${someString}")
            Log.d(TAG, "OnCreate: ${app}")
            MyApp()
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
                getRecipe()


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
    val items = listOf(Screen.Search, Screen.Planner, Screen.Progress, Screen.Menu)
    val shape = RoundedCornerShape(topLeft = 50f, topRight = 50f, bottomLeft = 50f, bottomRight = 50f)


               Scaffold(
                   backgroundColor = Color(0xff13354F),

//                   floatingActionButton = {
//                       AddReceipeButton()
//                   },
//                   floatingActionButtonPosition = FabPosition.Center,
//                   isFloatingActionButtonDocked = true,
//


                   bottomBar = {

                       BottomNavigation(
                           backgroundColor = Color(0xffffffff),


                           modifier = Modifier
                               .height(60.dp)

                       ) {
                           val navBackStackEntry by navController.currentBackStackEntryAsState()
                           val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                           val tintColor = Color(0xff6F6F6F)
                           items.forEach {
                               BottomNavigationItem(
//                                   unselectedContentColor = Color(0xffCCFF90),
//                                   selectedContentColor = Color(0xffCCFF90),

                                   icon = {
                                       when(it){
                                           Screen.Search -> Icon(imageResource(R.drawable.ic_search),"")
                                           Screen.Planner -> Icon(imageResource(R.drawable.ic_planner),"")
                                           Screen.Add -> Icon(imageResource(R.drawable.add_24), "", tint = tintColor)
                                           Screen.Progress -> Icon(imageResource(R.drawable.ic_progress), "")
                                           Screen.Menu -> Icon(imageResource(R.drawable.ic_menu),"")
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

    NavHost(navController = navController, startDestination = Screen.Search.route){
        composable(Screen.Search.route){ SearchScreen() }
        composable(Screen.Planner.route){ PlannerScreen() }
        //composable(Screen.Add.route){ AddScreen() }
        composable(Screen.Progress.route){ ProgressScreen() }
        composable(Screen.Menu.route){ MenuScreen() }
    }
}



fun showMessage(context: ContentContext, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun AddReceipeButton(){
    val icon = vectorResource(id = R.drawable.add_24)
    val context = AmbientContext.current
    FloatingActionButton(

        onClick = {
        showMessage(context, "Clicked The Add Recipe")
    }){
        Icon(
            icon, "", tint = Color(0xffe9e9e9)
//        asset = icon
        )
    }
}

@Composable
fun getRecipe(){


    val BASE_URL = "https://food2fork.ca/api/recipe/"
    val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(RecipeService::class.java)

    CoroutineScope(IO).launch {
        val recipe = service.get(
            token = TOKEN,
            id = 580
        )
        Log.d("MainActivity", "onCreate: ${recipe.title}")

    }

}


