package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter
import com.example.vinilos.repositories.PerformerRepository

class DetailPerformerViewModel (application: Application, id: Int, type: String) : AndroidViewModel(application){

    private val performerRepository = PerformerRepository(application)

    private val _performer = MutableLiveData<Performer>()

    val performer: LiveData<Performer>
        get() = _performer

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id: Int = id
    val type: String = type

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        performerRepository.refreshDataDetailPerformer(id, type, {
            _performer.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val id: Int, val type: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailPerformerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailPerformerViewModel(app, id, type) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}