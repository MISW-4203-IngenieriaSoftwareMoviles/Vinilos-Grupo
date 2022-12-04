package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Album
import com.example.vinilos.models.Track
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumRepository(val application: Application) {
    suspend fun refreshDataAlbums(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    suspend fun refreshDataDetailAlbum(id: Int): Album {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbum(id)
    }

    suspend fun refreshDataAssociateTrack(track: JSONObject): Track {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).associateTrack(track)
    }
}