package com.stargazer.bookexplorer.data

import kotlinx.coroutines.delay

class BookRepository {
    private val books = listOf(
        Book("1","Clean Code","Robert C. Martin","https://picsum.photos/300/400?1","…"),
        Book("2","Kotlin in Action","D. Jemerov","https://picsum.photos/300/400?2","…"),
        Book("3","Effective Kotlin","M. Moskala","https://picsum.photos/300/400?3","…")
    )

    suspend fun getBooks(): List<Book> { delay(500); return books }
    suspend fun getBook(id: String) = books.find { it.id == id }
    fun filterByName(q: String) = if (q.isBlank()) books else books.filter { it.title.contains(q, true) }
}