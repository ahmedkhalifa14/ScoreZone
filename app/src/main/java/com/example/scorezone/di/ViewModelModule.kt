package com.example.scorezone.di

import com.example.scorezone.data.network.SportApiService
import com.example.scorezone.qualifiers.Token
import com.example.scorezone.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
    @Provides
    @ViewModelScoped
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        @Token token: String = ""
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor { interceptor ->
            val request: Request = interceptor.request()
            val newReq = if (token.isNotEmpty()) {
                request.newBuilder()
                    .header("X-Auth-Token",token)
                    .method(request.method, request.body)
                    .build()
            } else {
                request.newBuilder()
                    .method(request.method, request.body)
                    .build()
            }
            interceptor.proceed(newReq)
        }
            .addNetworkInterceptor(interceptor)
            .build()
    @Provides
    @ViewModelScoped
    fun provideApiService(okHttpClient: OkHttpClient): SportApiService =
        Retrofit.Builder()
            .run {
                baseUrl(Constants.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(SportApiService::class.java)

    @Provides
    @ViewModelScoped
    @Token
    fun provideTokenUser(): String = "b0efb7e568c74f4aa329bb2e1d244946"
}