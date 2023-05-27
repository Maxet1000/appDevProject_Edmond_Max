package com.example.tracsit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val message = MutableLiveData<TravelInformation>()

    fun sendMessage(ti: TravelInformation) {
        message.value = ti
    }
}