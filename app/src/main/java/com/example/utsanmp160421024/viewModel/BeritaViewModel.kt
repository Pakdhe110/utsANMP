package com.example.utsanmp160421024.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.utsanmp160421024.model.Artikel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException

class BeritaViewModel(application: Application): AndroidViewModel(application) {
    val artikelLD = MutableLiveData<ArrayList<Artikel>>()
    val artikelLoadErrorLD = MutableLiveData<Boolean>()
    val loadLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        artikelLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/UTSanmp/get_artikel.php"
        Log.d("TAG", "Berita View Model")
        //172.17.160.1:8080
        val stringRequest  = StringRequest(
            Request.Method.GET, url,{
                try{
                    val sType = object : TypeToken<List<Artikel>>() { }.type
                    val result = Gson().fromJson<List<Artikel>>(it, sType)
                    artikelLD.value = result as ArrayList<Artikel>?
                    loadLD.value = false
                    Log.d("showfoley", result.toString())
                }
                catch (e:JSONException){
                    Log.d("TAG", "JSON ERROR: " + e)
                }

            },
            {
                Log.d("showfoley", it.toString())
                artikelLoadErrorLD.value = false
                loadLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun artikelDetail(id: String){
        artikelLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.me/hybrid/160421024/anmp/get_artikel_detail.php"

        val params = HashMap<String, String>()
        params["idArtikel"] = id

        val stringRequest  = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<List<Artikel>>() { }.type
                val result = Gson().fromJson<List<Artikel>>(it, sType)
                artikelLD.value = result as ArrayList<Artikel>?
                loadLD.value = false
                Log.d("showfoley", result.toString())
            },
            {
                Log.d("showfoley", it.toString())
                artikelLoadErrorLD.value = false
                loadLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}