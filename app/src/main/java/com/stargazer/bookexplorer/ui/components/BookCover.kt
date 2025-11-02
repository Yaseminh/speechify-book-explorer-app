package com.stargazer.bookexplorer.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun BookCover(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f), // README: 3:4 oran
        contentScale = ContentScale.Crop
    )
}