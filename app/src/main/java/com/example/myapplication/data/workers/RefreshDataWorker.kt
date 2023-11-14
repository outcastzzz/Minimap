package com.example.myapplication.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import javax.xml.transform.Result

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
): CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        TODO()
    }
}