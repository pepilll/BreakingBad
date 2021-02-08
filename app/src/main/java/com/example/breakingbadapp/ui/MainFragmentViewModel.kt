package com.example.breakingbadapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbadapp.models.CharacterData
import com.example.breakingbadapp.repository.MainRepository
import com.example.breakingbadapp.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainFragmentViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataStateLiveData: MutableLiveData<DataState<ArrayList<CharacterData>>> =
        MutableLiveData()
    val dataStateLiveData: LiveData<DataState<ArrayList<CharacterData>>>
        get() = _dataStateLiveData

    fun setStateEvent() {
        viewModelScope.launch {
            mainRepository.getCharacters()
                .onEach { dataState ->
                    _dataStateLiveData.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}