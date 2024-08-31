package my.ym.androidtutorials.utils

import androidx.annotation.StringRes
import my.ym.androidtutorials.R

object NavUtils {

    enum class Screen(val route: String, @StringRes val titleStringRes: Int) {
        HOME("Home", R.string.home),

        ;

        companion object {
            val startDestination = HOME
        }
    }

}
