package my.ym.androidtutorials.domain.posts

interface RepoPosts {

    suspend fun getPostsResponseAsString(): String

}
