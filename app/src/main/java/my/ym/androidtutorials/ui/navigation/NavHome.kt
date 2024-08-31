package my.ym.androidtutorials.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import my.ym.androidtutorials.ui.screens.HomeViewModel
import my.ym.androidtutorials.ui.screens.ScreenHome
import my.ym.androidtutorials.utils.NavUtils

private val screen = NavUtils.Screen.HOME

fun NavGraphBuilder.screenHome(
    navToScreen: (NavUtils.Screen) -> Unit,
) {
    composable(screen.route) {
        val viewModel = hiltViewModel<HomeViewModel>()

        ScreenHome(
            navToScreen = navToScreen,
            stableState = viewModel.stableState
        )
    }
}
