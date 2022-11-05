package com.example.planto_app.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.planto_app.MainActivity
import com.example.planto_app.PlantApp
import com.example.planto_app.R


class WaterReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    // Arbitrary id number
    private val notificationId = 11

    override fun doWork(): Result {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(applicationContext, 0, intent, 0)

        val plantName = inputData.getString(nameKey)

        val builder = NotificationCompat.Builder(applicationContext, PlantApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.plant_start_day)
            .setContentTitle("Water me!")
            .setContentText("It's time to water your $plantName")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationId, builder.build())
        }

        return Result.success()
    }

    companion object {
        const val nameKey = "NAME"
    }
}
