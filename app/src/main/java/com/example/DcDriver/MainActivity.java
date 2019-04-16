package com.example.DcDriver;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button btn1;
    private Button btn2;
    private Button loginBtn;
    private String name;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRefD = database.getReference("d2a");
    DatabaseReference myRefA = database.getReference("a2d");
    LoginManager loginManager = LoginManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(userInfo == null){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
           name = userInfo.getDisplayName();
            textView.setText(name + "님 반갑습니다.");
        }

        textView = (TextView) findViewById(R.id.textView);
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
//                myRefD.setValue(editText.getText().toString());
                myRefD.child("aaa").setValue(name);
                Intent intent = new Intent(MainActivity.this,MatchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myRefA.setValue(editText.getText().toString());
//                myRefA.child("bbb").push().setValue(editText.getText().toString());
                myRefA.child("bbb").setValue(name);
                Intent intent = new Intent(MainActivity.this,MatchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("aa", "User account deleted.");
                                }
                            }
                        });
                LoginManager.getInstance().logOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            }
        });
    }

}