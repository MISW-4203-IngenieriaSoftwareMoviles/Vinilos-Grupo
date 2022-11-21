package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
<<<<<<< HEAD
import com.example.vinilos.models.Album
=======
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter

class PerformerRepository (val application: Application) {
<<<<<<< HEAD
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

    fun refreshDataDetailPerformer(id: Int, callback: (Performer) -> Unit, onError: (VolleyError) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getPerformer(id,
            {
                //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
            onError
        )
=======
    suspend fun refreshDataPerformers(): List<Performer> {

        return NetworkServiceAdapter.getInstance(application).getBands()

    }

    suspend fun refreshDataDetailPerformer(id: Int, type: String): Performer {

        return NetworkServiceAdapter.getInstance(application).getPerformer(id, type)

>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
    }
}