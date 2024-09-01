package my.ym.androidtutorials.ui.screens.retrofitDynamicBaseUrl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.ym.androidtutorials.BuildConfig
import my.ym.androidtutorials.domain.general.RepoGeneral
import my.ym.androidtutorials.domain.posts.RepoPosts
import javax.inject.Inject

@HiltViewModel
class RetrofitDynamicBaseUrlViewModel @Inject constructor(
    private val repoGeneral: RepoGeneral,
    private val repoPosts: RepoPosts,
) : ViewModel() {

    val stableState = RetrofitDynamicBaseUrlStableState()


    var state: RetrofitDynamicBaseUrlState by mutableStateOf(RetrofitDynamicBaseUrlState.None)

    fun callApiGetPosts() {
        state = RetrofitDynamicBaseUrlState.LoadingApiCall

        viewModelScope.launch {
            if (BuildConfig.DEBUG) {
                // Just to show the loading state more.
                delay(1_000)
            }

            repoGeneral.setBaseUrl(stableState.baseUrl)

            state = when (val result = repoPosts.getPostsResponseAsString()) {
                "" -> {
                    RetrofitDynamicBaseUrlState.Error("Something went wrong please try again later")
                }
                else -> {
                    RetrofitDynamicBaseUrlState.Success(result)
                }
            }
        }
    }

}
