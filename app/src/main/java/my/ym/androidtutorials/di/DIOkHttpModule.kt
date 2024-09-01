package my.ym.androidtutorials.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.ym.androidtutorials.BuildConfig
import my.ym.androidtutorials.data.local.SharedPrefsGeneral
import my.ym.androidtutorials.utils.MyAppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIOkHttpModule {

    private const val TIMEOUT_IN_SEC = 15L

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        urlInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)

            addInterceptor(urlInterceptor)

            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Provides
    fun provideUrlInterceptor(
        sharedPrefsGeneral: SharedPrefsGeneral,
    ): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            val url = request.url.toString()

            val builder = request.newBuilder()

            // Replace base url & keep other paths & queries.
            val currentBaseUrl = sharedPrefsGeneral.getBaseUrl()

            builder.url(
                url.replace(MyAppConstants.API.BASE_URL_DUMMY_PLACEHOLDER, currentBaseUrl)
            )

            chain.proceed(builder.build())
        }
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.d("OkHttp3Log", message)
        }.also {
            it.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            }else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

}
