package com.person.sl.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.person.sl.view.CircularProgressView;
import com.person.sl.view.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            circle.setValue((float)(Math.random()*252),true);
        }
    };
    private CircularProgressView circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circle = (CircularProgressView) findViewById(R.id.circle);
        //circle.setValue(236,true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x1);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,1000,3000);
    }
}
