package com.example.DcDriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DMatchActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRefD = database.getReference("d2a");
    DatabaseReference myRefA = database.getReference("a2d");
    DatabaseReference myRefP = database.getReference("passenger");
    String name;
    String value;
    private Button BtnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmatch);
        name = userInfo.getDisplayName();

        Intent intent = getIntent();
        value = intent.getStringExtra("value");

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        BtnEnd = findViewById(R.id.BtnEnd);

        switch (value) {
            case "d2a":
                myRefP.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<RowInfo> rowInfoArrayList = new ArrayList<>();

                        HashMap<String, String> data = (HashMap<String, String>) dataSnapshot.getValue();

                        if(data != null){
                            rowInfoArrayList.add(new RowInfo("", data.get("d2a"), ""));
                        }else{
                            rowInfoArrayList.add(new RowInfo("", "대기중인 승객이 없습니다.", ""));
                        }

                        MyAdapter myAdapter = new MyAdapter(rowInfoArrayList);
                        mRecyclerView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("a", "Failed to read value.", error.toException());
                    }
                });
                break;
            case "a2d":
                myRefP.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<RowInfo> rowInfoArrayList = new ArrayList<>();

                        HashMap<String, String> data = (HashMap<String, String>) dataSnapshot.getValue();

                        if(data != null){
                            rowInfoArrayList.add(new RowInfo("", data.get("a2d"), ""));
                        }else{
                            rowInfoArrayList.add(new RowInfo("", "대기중인 승객이 없습니다.", ""));
                        }

                        MyAdapter myAdapter = new MyAdapter(rowInfoArrayList);
                        mRecyclerView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("a", "Failed to read value.", error.toException());
                    }
                });
                break;
        }

        BtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRefD.child("name").removeValue();
                myRefA.child("name").removeValue();
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRefD.child("name").removeValue();
        myRefA.child("name").removeValue();
        myRefD.child("carnum").removeValue();
        myRefA.child("carnum").removeValue();
    }
}
