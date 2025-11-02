package com.stargazer.bookexplorer.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stargazer.bookexplorer.ui.components.BookRow

@Composable
fun BookListScreen(
    onDetails: (String) -> Unit,
    onSearch: () -> Unit,
    vm: BookListViewModel = hiltViewModel()
) {
    val state = vm.ui.collectAsState().value
    var query by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())) {
        OutlinedTextField(
            value = query, onValueChange = { query = it; vm.onQueryChange(it) },
            modifier = Modifier.fillMaxWidth().padding(16.dp), label = { Text("Search by name") }
        )
        when (state) {
            ListUi.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
            is ListUi.Data -> LazyColumn {
                items((state as ListUi.Data).items, key = { it.id }) { b ->
                    BookRow(book = b, onDetails = onDetails)
                }
            }
            is ListUi.Err -> Text(text = (state as ListUi.Err).msg)
        }
    }
}