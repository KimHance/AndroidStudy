package com.example.viewmodelpractice.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel:ViewModel() {
    // 변경가능한 Mutable 타입의 LiveData
    private val _jangunScore : MutableLiveData<Int> = MutableLiveData()
    private val _mungunScore : MutableLiveData<Int> = MutableLiveData()
    private val _curSet : MutableLiveData<Int> = MutableLiveData()
    private val _jangunSetScore : MutableLiveData<Int> = MutableLiveData()
    private val _mungunSetScore : MutableLiveData<Int> = MutableLiveData()

    // Getter
    val curSet: LiveData<Int>
        get() = _curSet
    val jangunScore:LiveData<Int>
        get() =_jangunScore
    val mungunScore:LiveData<Int>
        get() =_mungunScore
    val jangunSetScore:LiveData<Int>
        get() =_jangunSetScore
    val mungunSetScore:LiveData<Int>
        get() =_mungunSetScore

    init {
        _jangunSetScore.value=0
        _mungunScore.value=0
        _mungunSetScore.value=0
        _jangunScore.value=0
        _curSet.value=1
    }


    // 팀 점수 +1
    fun jangunPlus() {
        _jangunScore.value = jangunScore.value?.plus(1)
    }
    fun mungunPlus() {
        _mungunScore.value = mungunScore.value?.plus(1)
    }


    // 팀 점수 -1
    fun jangunMinus() {
        _jangunScore.value = jangunScore.value?.minus(1)
    }
    fun mungunMinus() {
        _mungunScore.value = mungunScore.value?.minus(1)
    }

    // 세트수 +1
    fun jangunSetPlus(){
        _jangunSetScore.value = jangunSetScore.value?.plus(1)
        _curSet.value = _curSet.value?.plus(1)
    }
    fun mungunSetPlus(){
        _mungunSetScore.value = mungunSetScore.value?.plus(1)
        _curSet.value = _curSet.value?.plus(1)
    }


    fun getSet():LiveData<Int>{
        return curSet
    }

    fun getJangun():LiveData<Int>{
        return jangunScore
    }
    fun getMungun():LiveData<Int>{
        return mungunScore
    }

    fun getJangunSet():LiveData<Int>{
        return jangunSetScore
    }
    fun getMungunSet():LiveData<Int>{
        return mungunSetScore
    }


    fun scoreRollback(){
        _jangunScore.value=0
        _mungunScore.value=0
    }

    fun rollback(){
        _jangunScore.value=0
        _mungunScore.value=0
        _jangunSetScore.value=0
        _mungunSetScore.value=0
        _curSet.value=0
    }
}