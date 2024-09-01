package my.ym.androidtutorials.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiPostsServices {

    @GET("posts")
    suspend fun getPosts(): ResponseBody

}
