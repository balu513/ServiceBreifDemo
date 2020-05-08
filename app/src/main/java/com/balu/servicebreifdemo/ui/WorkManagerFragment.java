package com.balu.servicebreifdemo.ui;

import java.util.concurrent.TimeUnit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balu.servicebreifdemo.Constants;
import com.balu.servicebreifdemo.R;
import com.balu.servicebreifdemo.workmanager.MyWorker;

//https://www.simplifiedcoding.net/android-workmanager-tutorial/
public class WorkManagerFragment extends Fragment {

    private OneTimeWorkRequest oneTimeWorkRequest;
    private TextView tvStatus;
    private PeriodicWorkRequest periodicWorkRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_manager, container, false);
        initOneTimeWorkRequest(view);
        initPeriodicRequest(view);
        workInfoListener();
        setOnClickListners(view);
        return view;
    }

    private void setOnClickListners(View view) {
        view.findViewById(R.id.add_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enQueueRequest();
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelRequest();
            }
        });
    }

    private void initOneTimeWorkRequest(View view) {
        tvStatus = view.findViewById(R.id.tv_status);
        Data inputData = new Data.Builder().putString(Constants.ARG_EXTRA_PARAM, "BALUData from OneTimeReq").build();
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).setInputData(inputData).build();
    }

    private void initPeriodicRequest(View view) {
        Data inputData = new Data.Builder().putString(Constants.ARG_EXTRA_PARAM, "BALUData from PERIODIC").build();
        periodicWorkRequest = new PeriodicWorkRequest
                .Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                .setInputData(inputData)
                .build();
    }

    private void workInfoListener() {
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(getActivity(), new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                String outputData = workInfo.getOutputData().getString(Constants.FROM_WORKER);
                tvStatus.setText(workInfo.getState().name() + "\n\n" + outputData);
            }
        });
    }

    private void enQueueRequest() {
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
        WorkManager.getInstance().enqueue(periodicWorkRequest);

        //For Chain of works
//        WorkManager.getInstance().
//                beginWith(workRequest)
//                .then(workRequest1)
//                .then(workRequest2)
//                .enqueue();
    }

    private void cancelRequest() {
        WorkManager.getInstance().cancelWorkById(oneTimeWorkRequest.getId());
    }
}
