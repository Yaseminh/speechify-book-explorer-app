package com.stargazer.bookexplorer.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.stargazer.bookexplorer.data.Book

@Composable
fun BookRow(book: Book, onDetails: (String) -> Unit) {
    var pressed by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (pressed) 0.6f else 1f, label = "rowAlpha")

    Row(
        Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onDetails(book.id) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = null,
            modifier = Modifier
                .width(90.dp)
                .aspectRatio(3f / 4f), // 3:4 oran
            contentScale = ContentScale.Crop,
            alpha = alpha
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.fillMaxWidth()) {
            Text(book.title, style = MaterialTheme.typography.titleMedium)
            Text(book.author, style = MaterialTheme.typography.bodyMedium)
            Text("Details", color = MaterialTheme.colorScheme.primary)
        }
    }
}








