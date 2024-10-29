package my.ym.androidtutorials.data.local

interface SharedPrefsGeneral {

	suspend fun setBaseUrl(baseUrl: String)
	suspend fun getBaseUrl(): String

}
