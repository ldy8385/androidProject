package com.example.DcDriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DMatchActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRefD = database.getReference("d2a");
    DatabaseReference myRefA = database.getReference("a2d");
    String name;
    private Button BtnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmatch);
        name = userInfo.getDisplayName();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        BtnEnd = findViewById(R.id.BtnEnd);

        ArrayList<RowInfo> foodInfoArrayList = new ArrayList<>();
        foodInfoArrayList.add(new RowInfo(R.drawable.ic_launcher_background, "5,000원"));
        foodInfoArrayList.add(new RowInfo(R.drawable.ic_launcher_background, "4,600원"));
        foodInfoArrayList.add(new RowInfo(R.drawable.ic_launcher_background, "4,000원"));

        MyAdapter myAdapter = new MyAdapter(foodInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);

        BtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRefD.child(name).removeValue();
                myRefA.child(name).removeValue();
                finish();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        myRefD.child(name).removeValue();
        myRefA.child(name).removeValue();
    }
}
