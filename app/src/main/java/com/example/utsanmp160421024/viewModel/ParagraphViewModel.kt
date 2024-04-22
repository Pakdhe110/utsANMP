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
import com.example.utsanmp160421024.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class ParagraphViewModel(application: Application):AndroidViewModel(application) {
    val paragraphLD = MutableLiveData<ArrayList<Paragraf>>()
    val paragraphLoadErrorLD = MutableLiveData<Boolean>()
    val loadLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id: String){
        paragraphLoadErrorLD.value = false
        loadLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/UTSanmp/get_paragraph.php"
        val params = HashMap<String, String>()
        params["idArtikel"] = id

        Log.d("TAG","Berhasil Detail 1")

        val stringRequest  = object: StringRequest(
            Request.Method.GET, url,{
                json -> Log.d("SHOW VOLLEY", json)
                try{
                    val jsonObject = JSONObject(json)
                    val result = jsonObject.getString("result")
                    val jsonData = jsonObject.getJSONObject("data")
                    Log.d("TAG", "Berhasil Detail 2")

                    if (result == "OK"){
                        val sType = object : TypeToken<List<Paragraf>>() { }.type
                        val par = Gson().fromJson<List<Paragraf>>(jsonData.toString(), sType)
                        paragraphLD.value = par as ArrayList<Paragraf>?
                        loadLD.value = false
                        Log.d("SHOW VOLLEY", par.toString())
                        Log.d("TAG", "Berhasil Detail 3")
                    }
                    else{
                        Log.w("ERROR", "Unable to fetch data")
                        val msg = jsonObject.getString("message");
                        Log.d("TAG", msg)
                    }

                }
                catch (e:JSONException){
                    Log.d("TAG", "JSON Error: " + e)
                }
            },
            {
                Log.d("showfoley", it.toString())
                paragraphLoadErrorLD.value = false
                loadLD.value = false
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                return params
            }
        }

            stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}