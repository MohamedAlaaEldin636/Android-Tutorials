package my.ym.androidtutorials.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import my.ym.androidtutorials.ui.screens.retrofitDynamicBaseUrl.RetrofitDynamicBaseUrlViewModel
import my.ym.androidtutorials.ui.screens.retrofitDynamicBaseUrl.ScreenRetrofitDynamicBaseUrl
import my.ym.androidtutorials.utils.NavUtils

private val screen = NavUtils.Screen.RETROFIT_DYNAMIC_BASE_URL

fun NavGraphBuilder.screenRetrofitDynamicBaseUrl() {
    composable(screen.route) {
        val viewModel = hiltViewModel<RetrofitDynamicBaseUrlViewModel>()

        ScreenRetrofitDynamicBaseUrl(
            callApi = viewModel::callApiGetPosts,

            stableState = viewModel.stableState,
            state = viewModel.state,
        )
    }
}
