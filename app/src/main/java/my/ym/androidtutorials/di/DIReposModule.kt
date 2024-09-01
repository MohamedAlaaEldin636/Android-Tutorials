package my.ym.androidtutorials.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.ym.androidtutorials.data.local.SharedPrefsGeneral
import my.ym.androidtutorials.data.local.SharedPrefsGeneralImpl
import my.ym.androidtutorials.data.remote.RemoteDataSourcePosts
import my.ym.androidtutorials.domain.general.RepoGeneral
import my.ym.androidtutorials.domain.general.RepoGeneralImpl
import my.ym.androidtutorials.domain.posts.RepoPosts
import my.ym.androidtutorials.domain.posts.RepoPostsImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIReposModule {

	private const val SHARED_PREFS_KEY_FILE_NAME_OF_GENERAL_SETTINGS = "SHARED_PREFS_FILE_GENERAL_SETTINGS"

	@Provides
	fun provideRepoAuth(
		remoteDataSourcePosts: RemoteDataSourcePosts,
	): RepoPosts {
		return RepoPostsImpl(remoteDataSourcePosts)
	}

	@Provides
	fun provideRepoGeneral(
		sharedPrefsGeneral: SharedPrefsGeneral,
	): RepoGeneral {
		return RepoGeneralImpl(sharedPrefsGeneral)
	}

	@Singleton
	@Provides
	fun providePrefsGeneral(
		sharedPreferences: SharedPreferences,
	): SharedPrefsGeneral = SharedPrefsGeneralImpl(sharedPreferences)

	@Singleton
	@Provides
	fun provideSharedPrefs(
		@ApplicationContext context: Context
	): SharedPreferences {
		return context.getSharedPreferences(
			SHARED_PREFS_KEY_FILE_NAME_OF_GENERAL_SETTINGS,
			Context.MODE_PRIVATE
		)
	}

}
