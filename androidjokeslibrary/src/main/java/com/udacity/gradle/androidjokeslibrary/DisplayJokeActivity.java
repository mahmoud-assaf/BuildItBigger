package com.udacity.gradle.androidjokeslibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayJokeActivity extends AppCompatActivity {
TextView jokeDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!getIntent().hasExtra("joke")) {
            Toast.makeText(this,"sorry no joke to display" , Toast.LENGTH_LONG).show();
            finish();
        }
        setContentView(R.layout.activity_display_joke);
        jokeDisplay=findViewById(R.id.joke_textView);
        jokeDisplay.setText(getIntent().getStringExtra("joke"));
    }
}
