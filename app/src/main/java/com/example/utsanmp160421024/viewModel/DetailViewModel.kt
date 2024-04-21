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
import com.example.utsanmp160421024.model.Paragraf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val webLD = MutableLiveData<ArrayList<Paragraf>>()
    val webLoadErrorLD = MutableLiveData<Boolean>()
    val loadLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        webLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.0.2/website/web.json"

        val stringRequest  = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<List<Paragraf>>() { }.type
                val result = Gson().fromJson<List<Paragraf>>(it, sType)
                webLD.value = result as ArrayList<Paragraf>?
                loadLD.value = false
                Log.d("showfoley", result.toString())
            },
            {
                Log.d("showfoley", it.toString())
                webLoadErrorLD.value = false
                loadLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}