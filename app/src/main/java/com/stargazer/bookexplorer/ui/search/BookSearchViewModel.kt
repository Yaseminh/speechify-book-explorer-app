package com.stargazer.bookexplorer.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stargazer.bookexplorer.data.Book
import com.stargazer.bookexplorer.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _results = MutableStateFlow<List<Book>>(emptyList())
    val results: StateFlow<List<Book>> = _results

    init {
        // İlk yüklemede tüm kitapları getir
        viewModelScope.launch {
            _results.value = repo.getBooks()
        }
    }

    fun onQueryChange(q: String) {
        _query.value = q
        viewModelScope.launch {
            val all = repo.getBooks()
            _results.value = repo.filterByName(q)
        }
    }
}