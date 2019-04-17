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

public class PMatchActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRefD = database.getReference().child("d2a").child("name");
    DatabaseReference myRefA = database.getReference().child("d2a").child("name");
    DataSnapshot dataSnapshot;
    String name;
    String value;
    String id;
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

        ArrayList<RowInfo> rowInfoArrayList = new ArrayList<>();

        MyAdapter myAdapter = new MyAdapter(rowInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);

        myRefD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id = dataSnapshot.getValue(String.class);
                Log.w("a", "Value is: " + id);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("a", "Failed to read value.", error.toException());
            }
        });
        Log.w("das","dd"+id);
        if(value == "d2a"){
            rowInfoArrayList.add(new RowInfo(R.drawable.ic_launcher_background, "aaa"));
        }else if(value == "a2d"){
            rowInfoArrayList.add(new RowInfo(R.drawable.ic_launcher_background, "vvv"));
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
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        myRefD.child("name").removeValue();
        myRefA.child("name").removeValue();
    }
}
