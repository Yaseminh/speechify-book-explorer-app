package com.stargazer.bookexplorer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.stargazer.bookexplorer.ui.details.BookDetailsViewModel
import com.stargazer.bookexplorer.ui.details.DetailsUi

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(onBack: () -> Unit, vm: BookDetailsViewModel = hiltViewModel()) {
    val state = vm.ui.collectAsState().value
    Scaffold(topBar = { TopAppBar(title = { Text("Details") }, navigationIcon = { TextButton(onClick = onBack){ Text("Back") } }) }) { p ->
        when (state) {
            DetailsUi.Loading -> Box(Modifier.fillMaxSize().padding(p), contentAlignment = androidx.compose.ui.Alignment.Center) { CircularProgressIndicator() }
            is DetailsUi.Data -> {
                val b = (state as DetailsUi.Data).book
                Column(Modifier.fillMaxSize().padding(p).padding(16.dp)) {
                    AsyncImage(model = b.coverUrl, contentDescription = null,
                        modifier = Modifier.fillMaxWidth().aspectRatio(4f/3f), // README: 4:3
                        contentScale = ContentScale.Crop)
                    Spacer(Modifier.height(16.dp))
                    Text(b.title, style = MaterialTheme.typography.titleLarge)
                    Text(b.author, style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(12.dp))
                    Text(b.description)
                }
            }
            is DetailsUi.Err -> Text(text = (state as DetailsUi.Err).msg, modifier = Modifier.padding(p))
        }
    }
}