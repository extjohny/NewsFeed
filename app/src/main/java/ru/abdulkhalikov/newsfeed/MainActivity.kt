package ru.abdulkhalikov.newsfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.abdulkhalikov.newsfeed.presentation.MainScreen
import ru.abdulkhalikov.newsfeed.ui.theme.NewsFeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsFeedTheme {
                MainScreen()
            }
        }
    }
}