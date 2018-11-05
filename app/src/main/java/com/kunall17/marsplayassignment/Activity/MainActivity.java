package com.kunall17.marsplayassignment.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kunall17.marsplayassignment.Constants;
import com.kunall17.marsplayassignment.ImageAdapter;
import com.kunall17.marsplayassignment.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private List<String> pictureURLList;
    private TextView emptyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        emptyTV = findViewById(R.id.emptyTV);

        initializeRecycler();
    }

    private void initializeRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);

        pictureURLList = new ArrayList<>();

        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.show();

        adapter = new ImageAdapter(MainActivity.this, pictureURLList);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("SEEHERE", "onDataChange: ");
                progressDialog.dismiss();
                pictureURLList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String pictureURL = (String) postSnapshot.getValue();
                    pictureURLList.add(pictureURL);
                }
                adapter.notifyDataSetChanged();
                checkForEmptyView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
                progressDialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void checkForEmptyView() {
        if (adapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyTV.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyTV.setVisibility(View.GONE);
        }
    }
}
