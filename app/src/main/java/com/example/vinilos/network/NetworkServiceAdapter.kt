package com.example.vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.*
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://misw-4203-vinilos-grupo12.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums(
        onComplete: (resp: List<Album>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequestAlbums("albums",
                Response.Listener<String> { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Album>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Album(
                                albumId = item.getInt("id"),
                                name = item.getString("name"),
                                cover = item.getString("cover"),
                                recordLabel = item.getString("recordLabel"),
                                releaseDate = item.getString("releaseDate"),
                                genre = item.getString("genre"),
                                description = item.getString("description")
                            )
                        )
                    }
                    onComplete(list)
                },
                Response.ErrorListener {
                    onError(it)
                })
        )
    }

    fun getAlbum(
        id: Int,
        onComplete: (resp: Album) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequestAlbum("albums/$id",
                Response.Listener<String> { response ->
                    val item = JSONObject(response)
                    onComplete(
                        Album(
                            albumId = item.getInt("id"),
                            name = item.getString("name"),
                            cover = item.getString("cover"),
                            recordLabel = item.getString("recordLabel"),
                            releaseDate = item.getString("releaseDate"),
                            genre = item.getString("genre"),
                            description = item.getString("description")
                        )
                    )
                },
                Response.ErrorListener {
                    onError(it)
                })
        )
    }

    fun getCollectors(
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequestCollectors("collectors",
                Response.Listener<String> { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Collector>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        val performers = item.getJSONArray("favoritePerformers")
                        //val listFavoritePerformer = mutableListOf<Performer>()
                        /*for (i in 0 until performers.length()) {
                            val performer = performers.getJSONObject(i)
                            listFavoritePerformer.add(
                                i,
                                Performer(
                                    id = performer.getInt("id"),
                                    name = performer.getString("name"),
                                    image = performer.getString("image"),
                                    description = performer.getString("description"),
                                    birthDate = "",
                                    creationDate = "",
                                    type = ""
                                )
                            )
                        }*/
                        list.add(
                            i,
                            Collector(
                                id = item.getInt("id"),
                                name = item.getString("name"),
                                telephone = item.getString("telephone"),
                                email = item.getString("email")
                                //favoritePerformers = listFavoritePerformer
                            )
                        )
                    }
                    onComplete(list)
                },
                Response.ErrorListener {
                    onError(it)
                })
        )
    }

    fun getBands(onComplete:(resp:List<Performer>)->Unit, onError: (error:VolleyError)->Unit){
        val list = mutableListOf<Performer>()
        requestQueue.add(getRequestBands("bands",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Performer(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        creationDate = item.getString("creationDate"),
                        birthDate = "",
                        type = "Band"
                    ))
                }
            },
            Response.ErrorListener {
                onError(it)
            }))
        requestQueue.add(getRequestMusicians("musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Performer(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        creationDate = "",
                        birthDate = item.getString("birthDate"),
                        type = "Musician")
                    )
                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun getPerformer(
        id: Int,
        type: String,
        onComplete: (resp: Performer) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        if(type == "Band"){
            requestQueue.add(
                getRequestBand("bands/$id",
                    Response.Listener<String> { response ->
                        val item = JSONObject(response)
                        onComplete(
                            Performer(
                                id = item.getInt("id"),
                                name = item.getString("name"),
                                image = item.getString("image"),
                                description = item.getString("description"),
                                creationDate = item.getString("creationDate"),
                                birthDate = "",
                                type = "Band"
                            )
                        )
                    },
                    Response.ErrorListener {
                        onError(it)
                    })
            )
        }else{
            requestQueue.add(
                getRequestMusician("musicians/$id",
                    Response.Listener<String> { response ->
                        val item = JSONObject(response)
                        onComplete(
                            Performer(
                                id = item.getInt("id"),
                                name = item.getString("name"),
                                image = item.getString("image"),
                                description = item.getString("description"),
                                creationDate = "",
                                birthDate = item.getString("birthDate"),
                                type = "Musician"
                            )
                        )
                    },
                    Response.ErrorListener {
                        onError(it)
                    })
            )
        }

    }

    private fun getRequestAlbums(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }

    private fun getRequestAlbum(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }

    private fun getRequestCollectors(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }

    private fun getRequestBands(
        path:String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }

    private fun getRequestMusicians(
        path:String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }

    private fun getRequestBand(
        path:String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }

    private fun getRequestMusician(
        path:String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }

}