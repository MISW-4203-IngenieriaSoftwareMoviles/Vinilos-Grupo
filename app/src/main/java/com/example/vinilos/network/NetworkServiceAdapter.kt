package com.example.vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
<<<<<<< HEAD
import com.android.volley.VolleyError
=======
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont->
        val list = mutableListOf<Album>()
        requestQueue.add(
            getRequestAlbums("albums",
                Response.Listener<String> { response ->
                    val resp = JSONArray(response)
                    var item:JSONObject? = null
                    for (i in 0 until resp.length()) {
                        item = resp.getJSONObject(i)
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
                    cont.resume(list)
                },
                Response.ErrorListener {
                    cont.resumeWithException(it)
                })
        )
    }

    suspend fun getAlbum(
        id: Int) = suspendCoroutine<Album> { cont->
        requestQueue.add(
            getRequestAlbum("albums/$id",
                Response.Listener<String> { response ->
                    val item = JSONObject(response)
                    val album = Album(
                        albumId = item.getInt("id"),
                        name = item.getString("name"),
                        cover = item.getString("cover"),
                        recordLabel = item.getString("recordLabel"),
                        releaseDate = item.getString("releaseDate"),
                        genre = item.getString("genre"),
                        description = item.getString("description")

                    )
                    cont.resume(album)
                },
                Response.ErrorListener {
                    cont.resumeWithException(it)
                })
        )
    }

<<<<<<< HEAD
    fun getCollectors(
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
=======
    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont->
        val list = mutableListOf<Collector>()
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
        requestQueue.add(
            getRequestCollectors("collectors",
                Response.Listener<String> { response ->
                    val resp = JSONArray(response)
<<<<<<< HEAD
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
=======
                    var item:JSONObject? = null
                    for (i in 0 until resp.length()) {
                        item = resp.getJSONObject(i)
                        //val performers = item.getJSONArray("favoritePerformers")
                        list.add(
                            //i,
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
                            Collector(
                                id = item.getInt("id"),
                                name = item.getString("name"),
                                telephone = item.getString("telephone"),
                                email = item.getString("email")
<<<<<<< HEAD
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
=======
                            )
                        )
                    }
                    cont.resume(list)
                },
                Response.ErrorListener{
                    cont.resumeWithException(it)
                }))

    }

    suspend fun getBands() = suspendCoroutine<List<Performer>> { cont->
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
        val list = mutableListOf<Performer>()
        requestQueue.add(getRequestBands("bands",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
<<<<<<< HEAD
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
=======
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
                    list.add(i, Performer(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        creationDate = item.getString("creationDate"),
                        birthDate = "",
<<<<<<< HEAD
                        type = "Band"
                    ))
                }
            },
            Response.ErrorListener {
                onError(it)
=======
                        type = "Band")
                    )
                }
                //cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
            }))
        requestQueue.add(getRequestMusicians("musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
<<<<<<< HEAD
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
        onComplete: (resp: Performer) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        try{
        requestQueue.add(
            getRequestBand("bands/$id",
                Response.Listener<String> { response ->
                    val item = JSONObject(response)
                    onComplete(
                        Performer(
=======
                var item2:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item2 = resp.getJSONObject(i)
                    list.add(i,  Performer(
                        id = item2.getInt("id"),
                        name = item2.getString("name"),
                        image = item2.getString("image"),
                        description = item2.getString("description"),
                        creationDate = "",
                        birthDate = item2.getString("birthDate"),
                        type = "Musician")
                    )
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))

    }

    suspend fun getPerformer(
        id: Int,
        type: String
    ) = suspendCoroutine<Performer> { cont->
        if(type == "Band"){
            requestQueue.add(
                getRequestBand("bands/$id",
                    Response.Listener<String> { response ->
                        val item = JSONObject(response)
                        val performer = Performer(
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
                            id = item.getInt("id"),
                            name = item.getString("name"),
                            image = item.getString("image"),
                            description = item.getString("description"),
                            creationDate = item.getString("creationDate"),
                            birthDate = "",
                            type = "Band"
                        )
<<<<<<< HEAD
                    )
                },
                Response.ErrorListener {
                    onError(it)
                })
        )}catch (e:Exception){}
        try{
        requestQueue.add(
            getRequestMusician("musicians/$id",
                Response.Listener<String> { response ->
                    val item = JSONObject(response)
                    onComplete(
                        Performer(
=======
                        cont.resume(performer)
                    },
                    Response.ErrorListener {
                        cont.resumeWithException(it)
                    })
            )
        }else{
            requestQueue.add(
                getRequestMusician("musicians/$id",
                    Response.Listener<String> { response ->
                        val item = JSONObject(response)
                        val performer = Performer(
>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
                            id = item.getInt("id"),
                            name = item.getString("name"),
                            image = item.getString("image"),
                            description = item.getString("description"),
                            creationDate = "",
                            birthDate = item.getString("birthDate"),
                            type = "Musician"
                        )
<<<<<<< HEAD
                    )
                },
                Response.ErrorListener {
                    onError(it)
                })
        )}
        catch (e:Exception){}
=======
                        cont.resume(performer)
                    },
                    Response.ErrorListener {
                        cont.resumeWithException(it)
                    })
            )
        }

>>>>>>> 8c68ec70115452a691a30d41b3ba5359665a72d5
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