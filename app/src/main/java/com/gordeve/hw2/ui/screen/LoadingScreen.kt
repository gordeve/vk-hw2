package com.gordeve.hw2.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier.fillMaxSize())
}
