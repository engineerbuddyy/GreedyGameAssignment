package com.example.greedygamequoteappassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.greedygamequoteappassignment.data.FavoriteManager
import com.example.greedygamequoteappassignment.data.QuoteModel
import com.example.greedygamequoteappassignment.data.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val repository: QuoteRepository = QuoteRepository(),
    private val favoriteManager: FavoriteManager
) : ViewModel() {

    private val _quotes = MutableStateFlow<List<QuoteModel>>(emptyList())
    val quotes: StateFlow<List<QuoteModel>> = _quotes

    init {
        viewModelScope.launch {
            _quotes.value = repository.getQuotes()
        }
    }

    val favoriteIds = favoriteManager.favoriteIdsFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())

    fun toggleFavorite(id: String) {
        viewModelScope.launch { favoriteManager.toggleFavorite(id) }
    }

    fun isFavorite(id: String): Boolean = favoriteIds.value.contains(id)

    fun getFavoriteQuotes(): List<QuoteModel> =
        favoriteIds.value.mapNotNull { id -> quotes.value.find { it.id == id } }
}

class QuoteViewModelFactory(private val favoriteManager: FavoriteManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(QuoteRepository(), favoriteManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
