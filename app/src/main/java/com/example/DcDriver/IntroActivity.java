package com.example.DcDriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    private Button BtnDriver;
    private Button BtnPassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        BtnDriver = findViewById(R.id.button);
        BtnPassenger = findViewById(R.id.button2);

        BtnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, DMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        BtnPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, PMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
