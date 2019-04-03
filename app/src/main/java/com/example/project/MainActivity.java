package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button btn1;
    private Button btn2;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefD = database.getReference("d2a");
    DatabaseReference myRefA = database.getReference("a2d");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

    }

    @Override
    protected void onStart() {
        super.onStart();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRefD.setValue(editText.getText().toString());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRefA.setValue((editText.getText().toString()));
            }
        });
    }
}