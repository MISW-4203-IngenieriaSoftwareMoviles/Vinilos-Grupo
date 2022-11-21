package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter

class CollectorRepository(val application: Application) {
<<<<<<< HEAD
    fun refreshDataCollectors(callback: (List<Collector>) -> Unit, onError: (VolleyError) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getCollectors(
            {
                //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
            onError
        )
=======
    suspend fun refreshDataCollectors(): List<Collector>{
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getCollectors()
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
    }
}