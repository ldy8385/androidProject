package com.example.DcDriver;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ProfileManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DMainActivity extends AppCompatActivity {
    private TextView textView;
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
        setContentView(R.layout.activity_dmain);

        textView = (TextView) findViewById(R.id.textView);
        LinearLayout linearLayout = findViewById(R.id.layout);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        String data = sp.getString("info","");


        TextView textView2 = new TextView(this);
        EditText editText = new EditText(this);
        Button button = new Button(this);
        editText.setHint("차량번호를 입력해주세요");
        editText.setBackgroundColor(Color.WHITE);
        editText.setWidth(500);
        button.setWidth(500);
        button.setText("저장");
        textView2.setTextColor(Color.WHITE);


        if(data != ""){
            linearLayout.addView(textView2);
            textView2.setText("차량번호 : " + data);
            Log.e("aa","aa");
        }else{
            linearLayout.addView(editText);
            linearLayout.addView(button);
            Log.e("bb","bb");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("info",editText.getText().toString());
                    editor.commit();
                    editText.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    linearLayout.addView(textView2);
                    textView2.setText("차량번호 : " + data);

                }
            });
        }


        if(userInfo != null){
            name = userInfo.getDisplayName();
            textView.setText(name + "님 반갑습니다.");
            Log.e("sadsad",name);
            Log.e("sadsad",userInfo.toString());
        }else{
            Intent intent = new Intent(DMainActivity.this,LoginActivity.class);
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

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        String data = sp.getString("info","");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myRefD.setValue(editText.getText().toString());
                if(data != ""){
                    myRefD.child("name").setValue(name);
                    Intent intent = new Intent(DMainActivity.this, DMatchActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "차량번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myRefA.setValue(editText.getText().toString());
//                myRefA.child("bbb").push().setValue(editText.getText().toString());
                if(data != ""){
                    myRefA.child("name").setValue(name);
                    Intent intent = new Intent(DMainActivity.this, DMatchActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "차량번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("info", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

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
            Intent intent = new Intent(DMainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            }
        });
    }

}