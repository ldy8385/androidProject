package com.example.DcDriver;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

public class PMatchActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRefD = database.getReference().child("d2a");
    DatabaseReference myRefA = database.getReference().child("a2d");
    DatabaseReference myRefP = database.getReference("passenger");
    DataSnapshot dataSnapshot;
    String name;
    String value;
    //String id;
   String idd;
    private Button BtnEnd;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmatch);

        Intent intent = getIntent();
        value = intent.getStringExtra("value");

        Log.w("a", "넘어온 값 is: " + value);

        name = userInfo.getDisplayName();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        BtnEnd = findViewById(R.id.BtnEnd);

        switch (value) {
            case "d2a":
                myRefD.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<RowInfo> rowInfoArrayList = new ArrayList<>();

                        HashMap<String, String> data = (HashMap<String, String>) dataSnapshot.getValue();

                        if(data != null){
                            rowInfoArrayList.add(new RowInfo("", data.get("name"), data.get("carnum")));
                        }else{
                            rowInfoArrayList.add(new RowInfo("", "운행중인 드라이버가 없습니다.", ""));
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
            case "a2d" :
                myRefA.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<RowInfo> rowInfoArrayList = new ArrayList<>();

                        HashMap<String, String> data = (HashMap<String, String>) dataSnapshot.getValue();
                        if(data != null){
                            rowInfoArrayList.add(new RowInfo("", data.get("name"), data.get("carnum")));
                        }else{
                            rowInfoArrayList.add(new RowInfo("", "운행중인 드라이버가 없습니다.", ""));
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
                myRefP.child("d2a").removeValue();
                myRefP.child("a2d").removeValue();
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRefP.child("d2a").removeValue();
        myRefP.child("a2d").removeValue();
    }
}
