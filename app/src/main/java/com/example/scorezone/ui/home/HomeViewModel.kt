package com.example.scorezone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scorezone.models.Match
import com.example.scorezone.repository.ApiRepoImpl
import com.example.scorezone.utils.Event
import com.example.scorezone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val repo: ApiRepoImpl
): ViewModel() {
    private val _allMatchState =
        MutableStateFlow<Event<Resource<Match>>>(Event(Resource.Init()))
    val allMatchState: MutableStateFlow<Event<Resource<Match>>> =
        _allMatchState

    fun getAllMatches(){
        viewModelScope.launch (Dispatchers.Main){
            _allMatchState.emit(Event(Resource.Loading()))
            val result =repo.getAllMatches()
            _allMatchState.emit(Event(result))
        }
    }
}