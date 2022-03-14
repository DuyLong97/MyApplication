package com.example.myapplication.di

import com.example.myapplication.data.remote.ApiCallAdapterFactory
import com.example.myapplication.data.remote.PokemonService
import com.example.myapplication.repository.MainRepository
import com.example.myapplication.ui.main.MainRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain->
                val request = chain.request()
                val newRequest = request.newBuilder().url(request.url).build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesPokemonService(retrofit: Retrofit) : PokemonService {
        return retrofit.create(PokemonService::class.java)
    }

    @Provides
    @Singleton
    fun providesMainRepository(pokemonService: PokemonService) : MainRepository {
        return MainRepositoryImp(pokemonService)
    }

}