package com.stargazer.bookexplorer.ui.details
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stargazer.bookexplorer.data.Book
import com.stargazer.bookexplorer.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DetailsUi { object Loading: DetailsUi; data class Data(val book: Book): DetailsUi; data class Err(val msg:String): DetailsUi }

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val repo: BookRepository,
    saved: SavedStateHandle
) : ViewModel() {
    private val id: String = checkNotNull(saved["bookId"])
    private val _ui = MutableStateFlow<DetailsUi>(DetailsUi.Loading)
    val ui: StateFlow<DetailsUi> = _ui

    init {
        viewModelScope.launch {
            val b = repo.getBook(id)
            _ui.value = b?.let { DetailsUi.Data(it) } ?: DetailsUi.Err("Not found")
        }
    }
}