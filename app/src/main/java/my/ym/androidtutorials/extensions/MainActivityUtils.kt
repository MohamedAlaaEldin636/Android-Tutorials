package my.ym.androidtutorials.extensions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import my.ym.androidtutorials.ui.navigation.screenHome
import my.ym.androidtutorials.ui.navigation.screenRetrofitDynamicBaseUrl

fun NavGraphBuilder.navHostScreens(navController: NavController) {
    screenHome(
        navToScreen = {
            navController.navigate(it.route)
        }
    )

    screenRetrofitDynamicBaseUrl()
}
