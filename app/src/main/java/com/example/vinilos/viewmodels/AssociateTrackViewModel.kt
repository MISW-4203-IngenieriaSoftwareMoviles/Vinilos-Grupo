package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Album
import com.example.vinilos.models.Track
import com.example.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class AssociateTrackViewModel(application: Application, id: Int, track: JSONObject) : AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    private val _track = MutableLiveData<Track>()

    val id: Int = id
    val track: JSONObject = track

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        associateTrackFromNetwork(track)
    }

    private fun associateTrackFromNetwork(track: JSONObject):Int {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = albumsRepository.refreshDataAssociateTrack(track, id)
                    _track.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
        return id
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val id: Int, val track: JSONObject) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AssociateTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AssociateTrackViewModel(app, id, track) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}