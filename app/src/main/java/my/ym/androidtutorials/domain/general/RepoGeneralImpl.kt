package my.ym.androidtutorials.domain.general

import my.ym.androidtutorials.data.local.SharedPrefsGeneral

class RepoGeneralImpl(
    private val sharedPrefsGeneral: SharedPrefsGeneral,
) : RepoGeneral {

    override suspend fun setBaseUrl(baseUrl: String) {
        sharedPrefsGeneral.setBaseUrl(baseUrl)
    }

}