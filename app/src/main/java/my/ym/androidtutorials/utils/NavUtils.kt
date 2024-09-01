package my.ym.androidtutorials.utils

import androidx.annotation.StringRes
import my.ym.androidtutorials.R

object NavUtils {

    enum class Screen(val route: String, @StringRes val titleStringRes: Int) {
        HOME("Home", R.string.home),

        RETROFIT_DYNAMIC_BASE_URL("Retrofit Dynamic Base Url", R.string.retrofit_dynamic_base_url),

        ;

        companion object {
            val startDestination = HOME
        }
    }

}
