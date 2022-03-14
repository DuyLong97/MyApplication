package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.myapplication.data.remote.ApiResponse
import com.example.myapplication.model.Result
import com.example.myapplication.usecase.main.FetchPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchPokemonListUseCase: FetchPokemonListUseCase) : ViewModel(){

//    val pokemon = LivePagedListBuilder(userDataSource, pagedListConfig()).build()
    private val _listPokemon: MutableLiveData<List<Result>> = MutableLiveData()
    val listPokemon: LiveData<List<Result>> = _listPokemon

    init {
        fetchPokemonList()
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    private fun fetchPokemonList() = viewModelScope.launch(Dispatchers.IO) {
        fetchPokemonListUseCase().collect {
            if (it is ApiResponse.ResponseSuccess) {
                Log.d("TAG", "fetchPokemonList: true >>> ${it.response.body()}")
                _listPokemon.postValue(it.response.body()?.results)
            }
            else Log.d("TAG", "fetchPokemonList: false >>> error")
        }
    }
}