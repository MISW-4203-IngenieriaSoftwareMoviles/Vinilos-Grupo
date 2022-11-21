package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter

class PerformerRepository (val application: Application) {
    suspend fun refreshDataPerformers(): List<Performer> {

        return NetworkServiceAdapter.getInstance(application).getBands()

    }

    suspend fun refreshDataDetailPerformer(id: Int, type: String): Performer {

        return NetworkServiceAdapter.getInstance(application).getPerformer(id, type)

    }
}