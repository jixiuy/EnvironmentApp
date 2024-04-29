package com.example.enviroment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.enviroment.ui.theme.EnviromentTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnviromentTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { MyBottomNavigation(navController) }) {
                    NavHost(navController = navController, startDestination = "screen_0") {
                        composable("screen_0") { HomeScreen() }
                        composable("screen_1") { ExploreScreen() }
                        composable("screen_2") { InputScreen() }
                        composable("screen_3") { ProfileScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun MyBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavigationItemInfo("监控选择", Icons.Filled.Home),
        BottomNavigationItemInfo("历史查询", Icons.Filled.Person),
        BottomNavigationItemInfo("录入信息", Icons.Filled.Create),
        BottomNavigationItemInfo("用户设置", Icons.Filled.Settings)
    )
    var selectedItem by remember { mutableStateOf(0) }

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = index == selectedItem,
                onClick = {
                    selectedItem = index
                    navController.navigate("screen_$index")
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}

data class BottomNavigationItemInfo(val label: String, val icon: ImageVector)
