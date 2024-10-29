package my.ym.androidtutorials.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class SharedPrefsGeneralImpl(
    private val dataStore: DataStore<Preferences>
) : SharedPrefsGeneral {

    companion object {
        private val keyBaseUrl = stringPreferencesKey("KEY_BASE_URL")
    }

    override suspend fun setBaseUrl(baseUrl: String) {
        dataStore.edit { settings ->
            settings[keyBaseUrl] = baseUrl
        }
    }

    override suspend fun getBaseUrl(): String {
        return dataStore.data.map { preferences ->
            preferences[keyBaseUrl]
        }.firstOrNull().orEmpty()
    }

}