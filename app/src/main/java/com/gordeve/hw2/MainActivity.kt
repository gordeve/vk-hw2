package com.gordeve.hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cataas.Cat
import cataas.CataasService
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gordeve.hw2.ui.theme.Hw2Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cataasService = CataasService()

        enableEdgeToEdge()
        setContent {
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
                    MainScreen(cataasService, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(cataasService: CataasService, modifier: Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var cats by rememberSaveable { mutableStateOf<List<Cat>?>(null) }
    LaunchedEffect(key1=Unit) {
        coroutineScope.launch {
            cats = cataasService.getCats(skip = 0, limit = 30)
        }
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 3.dp,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = modifier
    ) {
        items (cats ?: emptyList()) { cat ->
            CatImage(cat, Modifier)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatImage(cat: Cat, modifier: Modifier) {
    GlideImage(
        model="https://cataas.com/cat/${cat.id}",
        contentDescription = cat.id,
        modifier=modifier,
        loading=placeholder(
            ColorPainter(Color.Red)
        )
    )
}