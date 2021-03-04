package com.example.backgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.backgroundthread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    ActivityMainBinding activityMainBinding;
    boolean isStart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        activityMainBinding.button.setOnClickListener(this::onClick);
        activityMainBinding.button2.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
       /* BackgroundThread backgroundThread=new BackgroundThread();
        backgroundThread.start();*/

        switch (v.getId()){
            case R.id.button:
                isStart=false;
                BackgroundThreadRunnable  runnable=new BackgroundThreadRunnable();
                new Thread(runnable).start();
                break;
            case R.id.button2:
                isStart=true;
                break;

        }


        // application will not responding due to background thread and app will be crash
        /*for (int i = 0; i <= 10; i++) {
            Log.d(TAG, "startthread"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    // handle background Thread using Thread in android
    public  class BackgroundThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i = 0; i <= 10; i++) {

                if(i==7){
                    activityMainBinding.button.setText("70%");
                }
                Log.d(TAG, "startthread"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // handle thread using runnable
    public class BackgroundThreadRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                Log.d(TAG, "startthread"+i);
                if(isStart){
                    return;
                }
                if(i==7){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activityMainBinding.button.setText("Wawo");
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}