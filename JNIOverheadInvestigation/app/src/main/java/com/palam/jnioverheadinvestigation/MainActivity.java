package com.palam.jnioverheadinvestigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.palam.jnioverheadinvestigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    TextView tvWarmup;
    TextView tvNOP;
    TextView tvSomeCalculations;
    TextView tvSuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWarmup = findViewById(R.id.tvWarmup);
        tvNOP = findViewById(R.id.tvNop);
        tvSomeCalculations = findViewById(R.id.tvSomeCalculations);
        tvSuit = findViewById(R.id.tvSuit);

        Button buttonWarmup = findViewById(R.id.buttonWarmup);
        Button buttonNOP = findViewById(R.id.buttonNop);
        Button buttonSomeCalculations = findViewById(R.id.buttonSomeCalculations);
        Button buttonSuit = findViewById(R.id.buttonSuit);

        buttonWarmup.setOnClickListener(v -> tvWarmup.setText(profileWarmup()));
        buttonNOP.setOnClickListener(v -> tvNOP.setText(profileNop()));
        buttonSomeCalculations.setOnClickListener(v -> tvSomeCalculations.setText(profileSomeCalculations()));
        buttonSuit.setOnClickListener(v -> tvSuit.setText(profileSuit()));
    }

    String profileWarmup() {
        final long PROFILE_CALL_COUNT = 10;
        long startTime = System.nanoTime();

        for (int i = 0; i < PROFILE_CALL_COUNT / 2; ++i) {
            String val1 = nop_jni();
            String val2 = someCalculations_jni();
        }
        long elapsedNs = (System.nanoTime() - startTime);
        long avgTime = elapsedNs / PROFILE_CALL_COUNT;
        String result = "Avg Time(ns): " + avgTime;
        return result;
    }

    String profileNop() {
        final long PROFILE_CALL_COUNT = 1000;
        long startTime = System.nanoTime();

        for (int i = 0; i < PROFILE_CALL_COUNT; ++i) {
            String val = nop_jni();
        }
        long elapsedNs = (System.nanoTime() - startTime);
        long avgTime = elapsedNs / PROFILE_CALL_COUNT;
        String result = "Avg Time(ns): " + avgTime;
        return result;
    }

    String profileSomeCalculations() {
        final long PROFILE_CALL_COUNT = 1000;
        long startTime = System.nanoTime();

        for (int i = 0; i < PROFILE_CALL_COUNT; ++i) {
            String val = someCalculations_jni();
        }
        long elapsedNs = (System.nanoTime() - startTime);
        long avgTime = elapsedNs / PROFILE_CALL_COUNT;
        String result = "Avg Time(ns): " + avgTime;
        return result;
    }

    String profileSuit() {
        String timeWarmup = profileWarmup();
        String timeNop = profileNop();
        String timeSomeCalculations = profileSomeCalculations();

        String suitResult = "Suit Result:\nWarmup " + timeWarmup + "\nNop " + timeNop + "\nSomeCalculations " + timeSomeCalculations;
        return suitResult;
    }

    public native String nop_jni();

    public native String someCalculations_jni();
}