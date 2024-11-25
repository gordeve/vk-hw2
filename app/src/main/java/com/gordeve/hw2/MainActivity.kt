package com.gordeve.hw2

import CatList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.gordeve.hw2.ui.theme.Hw2Theme
import androidx.compose.runtime.getValue
import com.gordeve.hw2.ui.screen.ErrorScreen
import com.gordeve.hw2.ui.screen.LoadingScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MainViewModel>()

        enableEdgeToEdge()
        setContent {
            val isLoading by viewModel.isLoading.collectAsState()
            val error by viewModel.error.collectAsState()
            val cats by viewModel.cats.collectAsState()
            LaunchedEffect(key1=Unit) {
                if (cats.isEmpty()) {
                    viewModel.fetchCats()
                }
            }
            Hw2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(getString(R.string.app_name))
                            }
                        )
                    }
                ) { innerPadding ->
                    when {
                        isLoading -> LoadingScreen(Modifier.padding(innerPadding))
                        error != null -> ErrorScreen(error!!, viewModel::fetchCats, Modifier.padding(innerPadding))
                        else -> CatList(cats, Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
