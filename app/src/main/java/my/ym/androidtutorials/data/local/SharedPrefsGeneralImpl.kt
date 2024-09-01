package my.ym.androidtutorials.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefsGeneralImpl(
    private val sharedPrefs: SharedPreferences,
) : SharedPrefsGeneral {

    companion object {
        private const val KEY_BASE_URL = "KEY_BASE_URL"
    }

    override fun setBaseUrl(baseUrl: String) {
        sharedPrefs.edit(true) {
            putString(KEY_BASE_URL, baseUrl)
        }
    }

    override fun getBaseUrl(): String {
        return sharedPrefs.getString(KEY_BASE_URL, null).orEmpty()
    }

}