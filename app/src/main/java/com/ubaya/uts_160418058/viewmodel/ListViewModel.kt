package com.ubaya.uts_160418058.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.uts_160418058.model.*
import com.ubaya.uts_160418058.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val TodoLD = MutableLiveData<List<Todo>>()
//   val dailyLD = MutableLiveData<List<Todo>>()
//   val weeklyLD = MutableLiveData<List<Todo>>()
//   val monthlyLD = MutableLiveData<List<Todo>>()
//   val historyLD = MutableLiveData<List<Todo>>()
    val taskLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job= Job()
    private var TAG = "volleyTag"
    private var queue: RequestQueue?= null


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refreshDaily() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())

            TodoLD.value = db.todoDao().selectAllDaily()
        }
    }

    fun refreshWeekly() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())

            TodoLD.value = db.todoDao().selectAllWeekly()
        }
    }
    fun refreshMonthly() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())

            TodoLD.value = db.todoDao().selectAllMonthly()
        }
    }
    fun refreshHistory() {
        taskLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())
            TodoLD.value = db.todoDao().selectAllHistory()
        }
    }
    fun addTodo(todo: Todo)
    {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }

    }
    fun addDummyTodo(list:List<Todo>)
    {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(*list.toTypedArray())
        }

    }
    fun deleteDummyTodo()
    {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().DeleteAll()
        }

    }
//    fun refreshDaily() {
//        taskLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication() )
//        val url = "https://api.npoint.io/97780c51b3bf76357df5"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<daily>>() { }.type
//                val result = Gson().fromJson<List<daily>>(response, sType)
//                dailyLD.value = result
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
//    fun refreshWeekly() {
//        taskLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication() )
//        val url = "https://api.npoint.io/93dda19adeac8e3a11d5"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<weekly>>() { }.type
//                val result = Gson().fromJson<List<weekly>>(response, sType)
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
//    fun refreshMonthly() {
//        taskLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication() )
//        val url = "https://api.npoint.io/954b977f2218245c726f"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<monthly>>() { }.type
//                val result = Gson().fromJson<List<monthly>>(response, sType)
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
//    fun refreshHistory() {
//        taskLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication() )
//        val url = "https://api.npoint.io/2e71da2e8f4cf7afa735"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<history>>() { }.type
//                val result = Gson().fromJson<List<history>>(response, sType)
//                historyLD.value = result
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