package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumRepository(val application: Application) {
    fun refreshDataAlbums(callback: (List<Album>) -> Unit, onError: (VolleyError) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums(
            {
                //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
            onError
        )
    }

    suspend fun refreshDataDetailAlbum(id: Int): Album {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbum(id)
    }
}