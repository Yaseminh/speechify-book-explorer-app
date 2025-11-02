package com.stargazer.bookexplorer.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stargazer.bookexplorer.data.Book
import com.stargazer.bookexplorer.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface ListUi { object Loading: ListUi; data class Data(val items: List<Book>): ListUi; data class Err(val msg:String): ListUi }

@HiltViewModel
class BookListViewModel @Inject constructor(private val repo: BookRepository) : ViewModel() {
    private val _ui = MutableStateFlow<ListUi>(ListUi.Loading)
    val ui: StateFlow<ListUi> = _ui
    private var full: List<Book> = emptyList()

    init {
        viewModelScope.launch {
            full = repo.getBooks()
            _ui.value = ListUi.Data(full)
        }
    }

    fun onQueryChange(q: String) {
        // README: arama sırasında loading göstermeyin
        _ui.value = ListUi.Data(if (q.isBlank()) full else full.filter { it.title.contains(q, true) })
    }
}