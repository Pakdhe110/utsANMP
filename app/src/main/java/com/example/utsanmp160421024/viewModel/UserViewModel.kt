package com.example.utsanmp160421024.viewModel

import android.app.Application
import android.app.backup.BackupManager
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
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class UserViewModel(application: Application):AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadLD = MutableLiveData<Boolean>()
    val msgLD = MutableLiveData<String>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, pass: String){
        userLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/UTSanmp/login.php"

        val params = HashMap<String, String>()
        params["username"] = username
        params["pass"] = pass
        Log.d("TAG", "Berhasil Login 1")


        val stringRequest  = object: StringRequest(
            Request.Method.POST, url,{
                json -> Log.d("SHOW FOLLEY", json)
                try {
                    val jsonObject = JSONObject(json)
                    val result  = jsonObject.getString("result")
                    val data =  jsonObject.getJSONObject("data")

                    if(result == "OK"){
                        val sType = object : TypeToken<User>() {}.type
                        val user = Gson().fromJson<User>(data.toString(), sType)
                        userLD.value = user
                        Log.d("TAG", "Berhasil Login 2")
                        Log.d("SHOW FOLLEY", json)
                    }
                    else{
                        Log.d("TAG", "jsonObject Error")
                    }
                }
                catch (e:JSONException){
                    Log.d("TAG", "Error JSON : " + e)
                    val jsonObj = JSONObject(json)
                    Log.d("TAG", jsonObj.getString("Message"))
                }
            },
            {
                Log.d("showfoley", it.toString())
                userLoadErrorLD.value = false
                loadLD.value = false
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    fun register(username: String, password: String, nameFirst: String, nameLast: String){
        userLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/UTSanmp/register.php"

        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        params["firstName"] = nameFirst
        params["lastName"] = nameLast
        Log.d("TAG", "Berhasil Register 1")

        val stringRequest  = object: StringRequest(
            Request.Method.POST, url,{
                    json -> Log.d("SHOW FOLLEY", json)
                try {
                    val jsonObject = JSONObject(json)
                    val result  = jsonObject.getString("result")
                    val msg = jsonObject.getString("message")

                    if(result == "OK"){
                        val sType = object : TypeToken<String>() {}.type
                        msgLD.value = result
                        Log.d("TAG", "Berhasil Register 2")
                        Log.d("SHOW FOLLEY", result)
                    }
                    else{
                        Log.d("TAG", msg)
                    }
                }
                catch (e:JSONException){
                    Log.d("TAG", "Error JSON : " + e)

                }
            },
            {
                Log.d("showfoley", it.toString())
                userLoadErrorLD.value = false
                loadLD.value = false
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

}