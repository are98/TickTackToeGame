package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goWith1Player(View view) { //yksinpeli kämästä tekoälyä vastaan
        Intent intent = new Intent(this, OnePlayerActivity.class);
        startActivity(intent);
    }

    public void goWith2Player(View view) { //kaksi pelaajaa samalta näytöltä
        Intent intent = new Intent(this, TwoPlayerActivity.class);
        startActivity(intent);
    }
}
