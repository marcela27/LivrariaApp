package com.example.livrariaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.livrariaapp.data.AppContainer
import com.example.livrariaapp.ui.navigation.BookNavGraph
import com.example.livrariaapp.ui.theme.LivrariaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LivrariaAppTheme{

                val appContainer = AppContainer(applicationContext)
                val bookRepository = appContainer.bookRepository
                val navController = rememberNavController()
                BookNavGraph(navController = navController, bookRepository = bookRepository)            }
        }
    }
}
