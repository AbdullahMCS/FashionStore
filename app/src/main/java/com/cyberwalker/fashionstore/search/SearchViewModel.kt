package com.cyberwalker.fashionstore.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cyberwalker.fashionstore.data.ShopItem
import com.cyberwalker.fashionstore.data.source.RemoteSource.shopItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchResult =
        MutableStateFlow<ArrayList<ShopItem>>(ArrayList())
    val searchResult: StateFlow<ArrayList<ShopItem>> get() = _searchResult.asStateFlow()

    fun findItems(searchInput: String) {
        _searchResult.value.clear()
        for (i in shopItems) {
            if (
                i.name!!.contains(searchInput, true) ||
                i.price!!.contains(searchInput, true)
            ) {
                _searchResult.value.add(i)
            }
        }
        Log.d("SearchViewModel", "findItems: ${searchResult.value}")
    }
}