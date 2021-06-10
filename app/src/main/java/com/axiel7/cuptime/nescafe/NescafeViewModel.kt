package com.axiel7.cuptime.nescafe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NescafeViewModel : ViewModel() {

    private val _lines = MutableLiveData<Int>().apply {
        value = 0
    }
    val lines: LiveData<Int> = _lines

    fun setLines(lines: Int) {
        _lines.value = lines
    }

    fun linesToTime() : Int {
        return when(lines.value) {
            1 -> 9
            2 -> 12
            3 -> 15
            4 -> 21
            5 -> 23
            6 -> 28
            7 -> 37
            else -> 0
        }
    }
}