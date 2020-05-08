package com.balu.servicebreifdemo.workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.SystemClock;
import android.widget.Toast;

import com.balu.servicebreifdemo.Constants;
import com.balu.servicebreifdemo.R;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class  MyWorker extends Worker {

    private static final String TAG = "MyWorker";
    private static final String ARG_EXTRA_PARAM = "ARG_EXTRA_PARAM";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String inputData = getInputData().getString(Constants.ARG_EXTRA_PARAM);
        //Toast.makeText(getApplicationContext(),"doWork: "+inputData,Toast.LENGTH_SHORT).show();
        displayNotification(inputData, "Hey I finished my work");

        Data outputData = new Data.Builder().putString(Constants.FROM_WORKER, "from My Worker @Balu").build();
        return Result.success(outputData);
    }

    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify((int) SystemClock.uptimeMillis(), notification.build());
    }

    }