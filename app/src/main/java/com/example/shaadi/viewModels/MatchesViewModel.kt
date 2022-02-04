package com.example.shaadi.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaadi.models.Matches
import com.example.shaadi.repository.MatchesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MatchesViewModel(private val repository: MatchesRepository): ViewModel() {

    val matchesResponse: MutableLiveData<Matches> = MutableLiveData()

    fun getMatches(isConnected: Boolean) {
        viewModelScope.launch {
            var response = repository.getMatches(isConnected)
           matchesResponse.value = response
        }
    }

    fun updateMatches(matches: Matches) {
        viewModelScope.launch {
            repository.updateMatches(matches)
        }
    }
}