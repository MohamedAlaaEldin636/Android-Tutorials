package my.ym.androidtutorials.ui.screens.retrofitDynamicBaseUrl

sealed interface RetrofitDynamicBaseUrlState {

    val result: String

    data object None : RetrofitDynamicBaseUrlState {
        override val result = "No Result"
    }

    data object LoadingApiCall : RetrofitDynamicBaseUrlState {
        override val result = "Loading"
    }

    data class Success(override val result: String) : RetrofitDynamicBaseUrlState

    data class Error(override val result: String) : RetrofitDynamicBaseUrlState

}
