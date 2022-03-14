package com.example.myapplication.usecase.main

import com.example.myapplication.repository.MainRepository
import javax.inject.Inject

class FetchPokemonListUseCase @Inject constructor(private val repository: MainRepository) {
    suspend operator fun invoke() = repository.fetchPokemonList()
}