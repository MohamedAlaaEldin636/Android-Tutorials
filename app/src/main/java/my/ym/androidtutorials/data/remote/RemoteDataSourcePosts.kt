package my.ym.androidtutorials.data.remote

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourcePosts @Inject constructor(
    private val apiService: ApiPostsServices,
) {

    suspend fun getPostsResponseAsString() = kotlin.runCatching {
        apiService.getPosts().string()
    }.getOrElse {
        Log.e("RDS", "RDS -> getPostsResponseAsString -> $it")

        ""
    }

}
