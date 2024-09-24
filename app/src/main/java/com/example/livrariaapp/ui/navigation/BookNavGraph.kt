package com.example.livrariaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.livrariaapp.ui.BookScreen
import com.example.livrariaapp.ui.BookViewModelFactory
import com.example.livrariaapp.data.BookRepository
import com.example.livrariaapp.ui.BookViewModel

@Composable
fun BookNavGraph(navController: NavHostController, bookRepository: BookRepository) {
    val viewModel: BookViewModel = viewModel(factory = BookViewModelFactory(bookRepository))

    NavHost(navController, startDestination = "bookScreen") {
        composable("bookScreen") { BookScreen(viewModel) }
    }
}
