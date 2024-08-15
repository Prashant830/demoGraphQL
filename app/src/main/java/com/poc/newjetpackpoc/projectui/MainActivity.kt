package com.poc.newjetpackpoc.projectui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.example.newjetpackpoc.ui.theme.NewJetpackPocTheme
import com.poc.CountryQuery
import com.poc.newjetpackpoc.di.ProjectComponents
import com.poc.newjetpackpoc.networklayer.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    private val components = ProjectComponents();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewJetpackPocTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val countries by components.viewModel.countries.observeAsState(emptyList())
                    CountryList(countries = countries, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun CountryList(countries: List<CountryQuery.Country>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(countries) { country ->
                CountryItem(country = country)
            }
        }
    }

    @Composable
    fun CountryItem(country: CountryQuery.Country) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = MaterialTheme.shapes.medium
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
}