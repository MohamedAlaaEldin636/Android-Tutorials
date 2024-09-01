package my.ym.androidtutorials.ui.screens.retrofitDynamicBaseUrl

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import my.ym.androidtutorials.utils.MyAppConstants

@Stable
class RetrofitDynamicBaseUrlStableState(
    baseUrl: String = MyAppConstants.API.BASE_URL_FAKE_1,
) {

    var baseUrl by mutableStateOf(baseUrl)

}
