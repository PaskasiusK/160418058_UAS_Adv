package com.ubaya.uts_160418058.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.uts_160418058.model.daily
import com.ubaya.uts_160418058.model.history
import com.ubaya.uts_160418058.model.monthly
import com.ubaya.uts_160418058.model.weekly

class ListViewModel(application: Application): AndroidViewModel(application) {
    val dailyLD = MutableLiveData<List<daily>>()
    val weeklyLD = MutableLiveData<List<weekly>>()
    val monthlyLD = MutableLiveData<List<monthly>>()
    val historyLD = MutableLiveData<List<history>>()
    val taskLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var TAG = "volleyTag"
    private var queue: RequestQueue?= null
    fun refreshDaily() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication() )
        val url = "https://api.npoint.io/97780c51b3bf76357df5"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<daily>>() { }.type
                val result = Gson().fromJson<List<daily>>(response, sType)
                dailyLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                taskLoadErrorLD.value = true
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }
    fun refreshWeekly() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication() )
        val url = "https://api.npoint.io/93dda19adeac8e3a11d5"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<weekly>>() { }.type
                val result = Gson().fromJson<List<weekly>>(response, sType)
                weeklyLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                taskLoadErrorLD.value = true
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }
    fun refreshMonthly() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication() )
        val url = "https://api.npoint.io/954b977f2218245c726f"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<monthly>>() { }.type
                val result = Gson().fromJson<List<monthly>>(response, sType)
                monthlyLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                taskLoadErrorLD.value = true
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }
    fun refreshHistory() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication() )
        val url = "https://api.npoint.io/2e71da2e8f4cf7afa735"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<history>>() { }.type
                val result = Gson().fromJson<List<history>>(response, sType)
                historyLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                taskLoadErrorLD.value = true
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}