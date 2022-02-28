package com.rajaditya.fampayassignment

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rajaditya.fampay.network.core.ApiRepository
import com.rajaditya.fampay.network.responses.ContextualCardsResponse
import com.rajaditya.fampayassignment.utils.getIntArray
import com.rajaditya.fampayassignment.utils.putIntArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState = _uiState.asStateFlow()

    private val _cardsDisabled = MutableStateFlow<List<Int>>(emptyList())
    val cardsDisabled = _cardsDisabled.asStateFlow()

    private lateinit var blockedCards : IntArray

    init {
        fetchContextualCards()
        blockedCards = sharedPreferences.getIntArray(SHARED_PREFS_KEY)
        _cardsDisabled.value = blockedCards.toList()
    }

    fun fetchContextualCards(){
        _uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val response = ApiRepository.contextualCardsApi.getContextualCards()
                _uiState.value = UiState.Loaded(response)
            }
            catch (exception : Exception){
                _uiState.value = UiState.Error(exception.message)
            }
        }
    }

    fun temporaryDisableCard(cardId : Int){
        val list = _cardsDisabled.value.toMutableList()
        list.add(cardId)
        _cardsDisabled.value = list
    }

    fun permanentDisableCard(cardId: Int){
        val editor = sharedPreferences.edit()
        val list = blockedCards.toMutableList()
        list.add(cardId)
        editor.putIntArray(SHARED_PREFS_KEY, list.toIntArray())
        editor.apply()
    }

    sealed class UiState {
        object Empty : UiState()
        object Loading : UiState()
        class Loaded(val response: ContextualCardsResponse) : UiState()
        class Error(val message: String?) : UiState()
    }

    class MainViewModelFactory(private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
                return MainActivityViewModel(sharedPreferences) as T
            }
            throw IllegalArgumentException ("UnknownViewModel")
        }
    }

    companion object{
        const val SHARED_PREFS_KEY = "shared_prefs"
    }
}