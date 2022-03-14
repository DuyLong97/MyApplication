package com.example.myapplication.repository

import com.example.myapplication.data.remote.ApiResponse
import com.example.myapplication.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun fetchPokemonList(): Flow<ApiResponse<PokemonResponse>>
}