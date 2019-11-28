package com.example.ordermaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    protected static int SPLASH_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().
                postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent Ni = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(Ni);
                        finish();
                    }
                },SPLASH_TIME);



    }
}
