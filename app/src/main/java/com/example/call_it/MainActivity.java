package com.example.call_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //A Handler allows you to send and process Message and Runnable objects associated with a thread's MessageQueue.
        //Causes the Runnable r to be added to the message queue, to be run after the specified amount of time elapses.
        // The runnable will be run on the thread to which this handler is attached.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i  = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}