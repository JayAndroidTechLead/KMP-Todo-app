package com.tandroid

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tandroid.screens.detail.DetailScreen
import com.tandroid.screens.list.MainScreen
import com.tandroid.screens.webScreen.WebScreen
import kotlinx.serialization.Serializable

@Serializable
object MainDestination

@Serializable
data class DetailDestination(val objectId: Int)

@Serializable
data class WebDestination(val url: String?,val html: String?)

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = MainDestination) {
                composable<MainDestination> {
                    MainScreen(navigateToWebScreen = { webURL, html->
                        navController.navigate(WebDestination(webURL,html))
                    },navigateToDetails = { objectId ->
                        navController.navigate(DetailDestination(objectId))
                    })
                }
                composable<WebDestination> { backStackEntry ->
                    WebScreen(webURL = backStackEntry.toRoute<WebDestination>().url, html = backStackEntry.toRoute<WebDestination>().html,
                        navigateBack = {
                            navController.popBackStack()
                        })
                }
                composable<DetailDestination> { backStackEntry ->
                    DetailScreen(
                        objectId = backStackEntry.toRoute<DetailDestination>().objectId,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
