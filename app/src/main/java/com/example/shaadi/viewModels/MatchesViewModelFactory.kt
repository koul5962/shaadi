package com.example.shaadi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shaadi.repository.MatchesRepository

class MatchesViewModelFactory(private val repository: MatchesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MatchesViewModel(repository) as T
    }
}