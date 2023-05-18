package com.example.sampleapplication.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.mvvm.dataModel.DataModel
import com.example.sampleapplication.mvvm.repository.Repository
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    val repo = Repository()
    val result: MutableLiveData<DataModel> = MutableLiveData()
    val liveData: LiveData<DataModel>
        get() = result

    fun getData() = viewModelScope.launch {
        result.value = repo.repository()
    }
}