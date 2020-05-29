package com.example.qlsvfirebase;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.qlsvfirebase.adapter.MyAdapter;
import com.example.qlsvfirebase.object.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;
    private ArrayList<Student> studentArrayList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        studentArrayList = new ArrayList<>();

        getDataStudent();


    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataStudent();
    }

    private void getDataStudent() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbStudent");
        // Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Student student =data.getValue(Student.class);
                    student.setId(data.getKey());
                    studentArrayList.add(student);

                }
                if (studentArrayList != null) {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                   // recyclerView.setHasFixedSize(true);
                    // use a linear layout manager
                    linearLayoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    // specify an adapter (see also next example)
                    myAdapter = new MyAdapter(studentArrayList, context);
                    recyclerView.setAdapter(myAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(context, "Load data faile" + error.getMessage(), Toast.LENGTH_LONG).show();
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
