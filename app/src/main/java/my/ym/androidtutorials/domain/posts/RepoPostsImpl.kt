package my.ym.androidtutorials.domain.posts

import my.ym.androidtutorials.data.remote.RemoteDataSourcePosts

class RepoPostsImpl(
    private val remoteDataSourcePosts: RemoteDataSourcePosts,
) : RepoPosts {

    override suspend fun getPostsResponseAsString(): String {
        return remoteDataSourcePosts.getPostsResponseAsString()
    }

}
