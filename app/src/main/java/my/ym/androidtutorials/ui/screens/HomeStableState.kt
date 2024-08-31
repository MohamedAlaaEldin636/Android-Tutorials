package my.ym.androidtutorials.ui.screens

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import my.ym.androidtutorials.utils.NavUtils

@Stable
class HomeStableState(
    search: String = "",
    screens: List<NavUtils.Screen> = NavUtils.Screen.entries - NavUtils.Screen.startDestination,
) {

    var search by mutableStateOf(search)

    var screens by mutableStateOf(screens)

}
