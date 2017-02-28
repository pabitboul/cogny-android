package com.medicoom.cogny.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.medicoom.cogny.R;

public class MainActivity extends Activity {
    Runnable mRunnable;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();

            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
    }
}
