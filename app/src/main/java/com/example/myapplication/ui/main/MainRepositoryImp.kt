package com.example.myapplication.ui.main

import com.example.myapplication.data.remote.ApiResponse
import com.example.myapplication.data.remote.ApiResponse.Companion.onErrorSuspend
import com.example.myapplication.data.remote.ApiResponse.Companion.onSuccessSuspend
import com.example.myapplication.data.remote.PokemonService
import com.example.myapplication.model.PokemonResponse
import com.example.myapplication.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val pokemonService: PokemonService): MainRepository {
    override suspend fun fetchPokemonList(): Flow<ApiResponse<PokemonResponse>> {
        return flow {
            pokemonService.fetchPokemonList().onSuccessSuspend {
                response.body()?.let {
                    emit(ApiResponse.success(response))
                }
            }.onErrorSuspend {
                emit(ApiResponse.error<PokemonResponse>(""))
            }
        }
    }
}