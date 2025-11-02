package com.stargazer.bookexplorer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.navArgument
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*

import com.stargazer.bookexplorer.ui.list.BookListScreen
import com.stargazer.bookexplorer.ui.search.BookSearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppNav() }
    }
}

@Composable
fun AppNav() {
    val nav = rememberNavController()
    MaterialTheme {
        NavHost(nav, startDestination = "list") {
            composable("list") {
                BookListScreen(
                    onDetails = { id -> nav.navigate("details/$id") },
                    onSearch  = { nav.navigate("search") }
                )
            }
            composable(
                "details/{bookId}",
                arguments = listOf(navArgument("bookId"){ type = NavType.StringType })
            ) { BookDetailsScreen(onBack = { nav.popBackStack() }) }
            composable("search") { BookSearchScreen(onBack = { nav.popBackStack() }) }
        }
    }
}