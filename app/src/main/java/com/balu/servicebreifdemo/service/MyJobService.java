package com.balu.servicebreifdemo.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(getApplicationContext(),"onStartJob of MyJobService",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Toast.makeText(getApplicationContext(),"onStopJob of MyJobService",Toast.LENGTH_SHORT).show();
        return true;
    }
}
