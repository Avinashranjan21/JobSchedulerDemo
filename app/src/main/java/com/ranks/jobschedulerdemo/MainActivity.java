package com.ranks.jobschedulerdemo;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_job)
    Button btnJob;
    private static final String TAG = "MainActivity";
    private JobInfo jobInfo;
    private NotificationManager notifManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ComponentName componentName = new ComponentName(this, JobSchedulerService.class);
        jobInfo = new JobInfo.Builder(12, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // TODO: 20/02/18 here u will add ur scenario like mobile network, battery state
                .setRequiresCharging(true) // setting device must be in charging state for the job to be called
                .setPeriodic(15*60*1000)  // setting periodic of 15 min
                .build();
    }

    @OnClick(R.id.btn_job)
    public void onViewClicked() {
        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled!");
        } else {
            Log.d(TAG, "Job not scheduled");
        }
    }


}
