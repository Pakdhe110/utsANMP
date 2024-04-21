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
import com.example.utsanmp160421024.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserViewModel(application: Application):AndroidViewModel(application) {
    val userLD = MutableLiveData<ArrayList<User>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, pass: String){
        userLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://www.ubaya.me/hybrid/160421024/utsanmp/login.php"
        val params = HashMap<String, String>()
        params["username"] = username
        params["pass"] = pass

        val stringRequest  = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<List<User>>() { }.type
                val result = Gson().fromJson<List<User>>(it, sType)
                userLD.value = result as ArrayList<User>?
                loadLD.value = false
                Log.d("showfoley", result.toString())
            },
            {
                Log.d("showfoley", it.toString())
                userLoadErrorLD.value = false
                loadLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

}