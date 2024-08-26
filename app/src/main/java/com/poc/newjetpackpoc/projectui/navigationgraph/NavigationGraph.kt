package com.poc.newjetpackpoc.projectui.navigationgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poc.newjetpackpoc.projectui.CountryViewModel
import com.poc.newjetpackpoc.projectui.PostViewModel
import com.poc.newjetpackpoc.projectui.navigationgraph.pages.NotificationScreen
import com.poc.newjetpackpoc.projectui.navigationgraph.pages.ProfileScreen
import com.poc.newjetpackpoc.projectui.navigationgraph.pages.SettingsScreen

const val TAG = "NavGraph"

// navigation graph
@Composable
fun App( modifier: Modifier = Modifier , countryViewModel: CountryViewModel , postViewModel: PostViewModel){
    val navController =  rememberNavController()

    NavHost(navController =navController, startDestination = "Profile" ){
        composable("Profile") {
            ProfileScreen(navController, modifier, countryViewModel)
        }

        composable("Settings/{settingParam}") { backStackEntry ->
            val settingsId = backStackEntry.arguments?.getString("settingParam")
            if (settingsId != null) {
                SettingsScreen(navController , postViewModel , settingsId)
            }
        }

        composable("Notification/{notificationParam}") {
            backStackEntry ->
            val notificationId = backStackEntry.arguments?.getString("notificationParam")
            if (notificationId != null) {
                NotificationScreen(navController, notificationId)
            }
        }


    }
}
