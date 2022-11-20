package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter

class PerformerRepository (val application: Application) {
    fun refreshDataPerformers(callback: (List<Performer>) -> Unit, onError: (VolleyError) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getBands(
            {
                //Guardar las bandas de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
            onError
        )
    }

    suspend fun refreshDataDetailPerformer(id: Int, type: String): Performer {

        return NetworkServiceAdapter.getInstance(application).getPerformer(id, type)

    }
}