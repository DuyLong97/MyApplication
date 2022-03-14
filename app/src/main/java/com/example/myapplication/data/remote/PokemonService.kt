package com.example.myapplication.data.remote

import com.example.myapplication.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : ApiResponse<PokemonResponse>
}