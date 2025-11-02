package com.stargazer.bookexplorer.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stargazer.bookexplorer.ui.components.BookRow
import com.stargazer.bookexplorer.ui.components.EmptyState

@OptIn(ExperimentalMaterial3Api::class) // ✅ eklendi: TopAppBar, OutlinedTextField vs. için
@Composable
fun BookSearchScreen(
    onBack: () -> Unit,
    vm: BookSearchViewModel = hiltViewModel()
) {
    val query by vm.query.collectAsState()
    val results by vm.results.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Books") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { vm.onQueryChange(it) },
                label = { Text("Search by title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            if (results.isEmpty()) {
                EmptyState("No results found")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(results, key = { it.id }) { book ->
                        BookRow(book = book, onDetails = { /* optional */ })
                    }
                }
            }
        }
    }
}
