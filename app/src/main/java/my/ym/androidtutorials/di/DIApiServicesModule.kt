package my.ym.androidtutorials.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.ym.androidtutorials.data.remote.ApiPostsServices
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIApiServicesModule {

    @Provides
    @Singleton
    fun provideApiPostsServices(retrofit: Retrofit): ApiPostsServices =
        retrofit.create(ApiPostsServices::class.java)

}
