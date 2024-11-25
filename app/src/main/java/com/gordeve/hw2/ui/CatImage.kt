package com.gordeve.hw2.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.gordeve.hw2.cataas.Cat
import com.gordeve.hw2.BuildConfig

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatImage(cat: Cat, modifier: Modifier) {
    GlideImage(
        model= BuildConfig.API_URL + "/cat/${cat.id}",
        contentDescription = cat.id,
        modifier=modifier,
        contentScale = ContentScale.Crop,
        loading=placeholder(
            ColorPainter(Color.Red)
        )
    )
}