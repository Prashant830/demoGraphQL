package com.poc.newjetpackpoc.projectui.navigationgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poc.CountryQuery
import com.poc.newjetpackpoc.projectui.CountryViewModel

const val TAG = "NavGraph"


// first Screen and it's components
@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier , countryViewModel:CountryViewModel) {

    Column {
        Text("Profile Screen")
        Spacer(modifier = Modifier.fillMaxWidth())
        val countries by countryViewModel.countries.observeAsState()
        countries?.let { CountryList(countries = it, modifier = modifier, navController) }

    }
}

@Composable
fun CountryList(countries: List<CountryQuery.Country>, modifier: Modifier = Modifier, navController: NavController) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(countries) { country ->
            CountryItem(country = country, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryItem(country: CountryQuery.Country, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            navController.navigate("Settings")
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = country.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Code: ${country.code}, Emoji: ${country.emoji}, Phone: ${country.phone}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}


// Second Screen and it's components
@Composable
fun SettingsScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column {
        Text("Settings Screen")

        Spacer(modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            navController.navigate("Notification")
        }) {
        Text("Go to Notification Screen")
    }
        }
}


// Third Screen and it's components
@Composable
fun NotificationScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column {

        Text("Notification Screen")

        Spacer(modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            navController.navigate("Profile")
        }) {
        Text("Go to Profile Screen")
      }
    }
}


// navigation graph
@Composable
fun App( modifier: Modifier = Modifier , viewModel: CountryViewModel){
    val navController =  rememberNavController()

    NavHost(navController =navController, startDestination = "Profile" ){
        composable("Profile") {
            ProfileScreen(navController , modifier , viewModel)
        }

        composable("Settings") {
            SettingsScreen(navController)
        }

        composable("Notification") {
            NotificationScreen(navController)
        }

    }

}
