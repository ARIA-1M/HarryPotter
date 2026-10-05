package com.example.harrypotter.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.harrypotter.presentation.alternameedit.AlterNameEditScreen
import com.example.harrypotter.presentation.alternamelist.AlterNameListScreen
import com.example.harrypotter.presentation.characterlist.CharacterListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharacterList.router
    ) {
        composable(Screen.CharacterList.router) {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(Screen.AlterNameList.createRoute(characterId))
                }
            )
        }

        composable(
            route = Screen.AlterNameList.router,
            arguments = listOf(navArgument("characterId") { type = NavType.StringType })
        ) { backStack ->
            val characterId = backStack.arguments?.getString("characterId") ?: return@composable
            AlterNameListScreen(
                characterId = characterId,
                onBack = { navController.popBackStack() },
                onAddClick = {
                    navController.navigate(Screen.AlterNameEdit.createRoute(null, characterId))
                },
                onEditClick = { nameId ->
                    navController.navigate(Screen.AlterNameEdit.createRoute(nameId, characterId))
                }
            )
        }

        composable(
            route = Screen.AlterNameEdit.router,
            arguments = listOf(
                navArgument("nameId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("characterId") { type = NavType.StringType }
            )
        ) { backStack ->
            val nameId = backStack.arguments?.getInt("nameId")?.takeIf { it != -1 }
            val characterId = backStack.arguments?.getString("characterId") ?: return@composable
            AlterNameEditScreen(
                nameId = nameId,
                characterId = characterId,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

    }
}