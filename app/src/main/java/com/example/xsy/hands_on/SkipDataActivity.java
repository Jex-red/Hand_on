package com.example.xsy.hands_on;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SkipDataActivity extends AppCompatActivity {
    private TextView textView_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_data);
        textView_skip = (TextView) findViewById(R.id.textView_skip);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        textView_skip.setText(data);
    }
}
