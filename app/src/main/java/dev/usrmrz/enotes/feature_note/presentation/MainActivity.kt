package dev.usrmrz.enotes.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.usrmrz.enotes.feature_note.presentation.add_edit_note.AddEditNoteScreen
import dev.usrmrz.enotes.feature_note.presentation.notes.NotesScreen
import dev.usrmrz.enotes.feature_note.presentation.util.Screen
import dev.usrmrz.enotes.feature_note.presentation.util.Screen.NotesScreen
import dev.usrmrz.enotes.ui.theme.EnotesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnotesTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NotesScreen.route
                    ) {
                        composable(route = NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(route = Screen.AddEditNotesScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}
