package com.example.utsanmp160421024.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.utsanmp160421024.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ParagraphViewModel(application: Application):AndroidViewModel(application) {
    val paragraphLD = MutableLiveData<ArrayList<User>>()
    val paragraphLoadErrorLD = MutableLiveData<Boolean>()
    val loadLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        paragraphLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://www.ubaya.me/hybrid/160421024/UTSanmp/get_artikel.php"

        val stringRequest  = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<List<User>>() { }.type
                val result = Gson().fromJson<List<User>>(it, sType)
                paragraphLD.value = result as ArrayList<User>?
                loadLD.value = false
                Log.d("showfoley", result.toString())
            },
            {
                Log.d("showfoley", it.toString())
                paragraphLoadErrorLD.value = false
                loadLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}