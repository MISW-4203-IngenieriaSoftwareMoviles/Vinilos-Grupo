package com.example.vinilos.network
import android.content.Context
import com.example.vinilos.models.Performer
import com.example.vinilos.models.Album
import com.example.vinilos.models.Collector

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

}