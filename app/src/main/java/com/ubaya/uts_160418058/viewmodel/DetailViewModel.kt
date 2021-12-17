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
import com.ubaya.uts_160418058.model.Todo
//import com.ubaya.uts_160418058.model.daily
//import com.ubaya.uts_160418058.model.monthly
//import com.ubaya.uts_160418058.model.weekly
import com.ubaya.uts_160418058.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application): AndroidViewModel(application) , CoroutineScope {
//    val dailyLD = MutableLiveData<daily>()
//    val weeklyLD = MutableLiveData<weekly>()
//    val monthlyLD = MutableLiveData<monthly>()
    val TodoLD = MutableLiveData<Todo>()
    val taskLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val job = Job()

    fun fetch(uuid:Int) {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())
            TodoLD.value =  db.todoDao().selectTodo(uuid)
        }
    }
    fun update(title:String, description:String,  uuid:Int, category:String, priority:String, photo_url:String,tipe:String) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, description, uuid, category, priority, photo_url,tipe)
        }
    }
    fun done(uuid:Int)
    {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().updatedone(uuid)
        }
    }
    fun delete(obj : Todo)
    {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().deleteTodo(obj)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    fun fetchWeekly(id:String) {
//        taskLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication() )
//        val url = "https://api.npoint.io/93dda19adeac8e3a11d5/"+id
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<weekly>() { }.type
//                val result = Gson().fromJson<weekly>(response, sType)
//                weeklyLD.value = result
//                loadingLD.value = false
//                Log.d("showvoley", result.toString())
//
//            },
//            {
//                Log.d("showvoley", it.toString())
//                taskLoadErrorLD.value = true
//                loadingLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//
//
//    }
//    fun fetchMonthly(id:String) {
//        taskLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication() )
//        val url = "https://api.npoint.io/954b977f2218245c726f/"+id
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<monthly>() { }.type
//                val result = Gson().fromJson<monthly>(response, sType)
//                monthlyLD.value = result
//                loadingLD.value = false
//                Log.d("showvoley", result.toString())
//
//            },
//            {
//                Log.d("showvoley", it.toString())
//                taskLoadErrorLD.value = true
//                loadingLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//
//
//    }

}