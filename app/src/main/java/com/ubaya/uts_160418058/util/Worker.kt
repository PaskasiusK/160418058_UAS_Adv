package com.ubaya.uts_160418058.util

import android.content.Context
import androidx.work.WorkerParameters
import androidx.work.Worker

class Worker(val context: Context, val params: WorkerParameters):Worker(context,params){
    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString("TITLE").toString(),
            inputData.getString("MESSAGE").toString())
        return Result.success()
    }
}