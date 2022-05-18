package com.example.observarblecomparison

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _liveData = MutableLiveData("Hello World")
    val liveData: LiveData<String> = _liveData

    // 주로 값 저장 시
    private val _stateFlow = MutableStateFlow("Hello World")
    val stateFlow = _stateFlow.asStateFlow()

    // 주로 이벤트 발생 시
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun triggerLiveData(){
        _liveData.value = "LiveData"
    }

    fun triggerStateFlow(){
        _stateFlow.value = "StateFlow"
    }

    fun triggerFlow(): Flow<String>{
        return flow{
            repeat(5){
                emit("Item ${it+1}")
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow(){
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }

}