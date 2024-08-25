package com.poc.newjetpackpoc.projectui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newjetpackpoc.ui.theme.NewJetpackPocTheme
import com.poc.newjetpackpoc.projectui.navigationgraph.App
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val countryViewModel: CountryViewModel by viewModel()
    private val postViewModel: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewJetpackPocTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding) , countryViewModel , postViewModel)
                }
            }
        }
    }
}