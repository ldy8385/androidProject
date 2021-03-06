package com.example.DcDriver;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PMainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn1;
    private Button btn2;
    private Button loginBtn;
    private String name;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
    LoginManager loginManager = LoginManager.getInstance();
    DatabaseReference myRefP = database.getReference("passenger");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmain);

        textView = (TextView) findViewById(R.id.textView);

        if(userInfo != null){
            name = userInfo.getDisplayName();
            textView.setText(name + "님 안녕하세요.");
            Log.e("sadsad",name);
            Log.e("sadsad",userInfo.toString());
        }else{
            Intent intent = new Intent(PMainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            Log.e("asf","aa");
        }
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        loginBtn = findViewById(R.id.login_button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PMainActivity.this, PMatchActivity.class);
                intent.putExtra("value","d2a");
                myRefP.child("d2a").setValue(name);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PMainActivity.this, PMatchActivity.class);
                intent.putExtra("value","a2d");
                myRefP.child("a2d").setValue(name);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PMainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}