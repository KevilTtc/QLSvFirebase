package com.example.qlsvfirebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsvfirebase.R;
import com.example.qlsvfirebase.object.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityEdit extends AppCompatActivity {
    private EditText edtMass;
    private EditText hoTen;
    private EditText email;
    private EditText soDt;
    private Button btnHuy, btnBack, btnThem;
    private Student student;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        init();
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("SINHVIEN");
        if (student != null){
            edtMass.setText(student.getMssv());
            hoTen.setText(student.getHoTen());
            email.setText(student.getEmail());
            soDt.setText(student.getSoDT());
        }else {
            Toast.makeText(getApplicationContext(), "Load thất bại" , Toast.LENGTH_LONG).show();
        }



        initEvent();
        initBackAndHuy();
    }

    private void initBackAndHuy() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMass.setText("");
                hoTen.setText("");
                email.setText("");
                soDt.setText("");
            }
        });
    }

    private void initEvent() {

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputMass = edtMass.getText().toString().trim();
                String inputHoTen = hoTen.getText().toString().trim();
                String inputEmail = email.getText().toString().trim();
                String inputSoDT = soDt.getText().toString().trim();
                String id = student.getId();


                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("DbStudent");
                // Read from the database
                // tạo 1 id ngẫu nhiên trên firebase

                myRef.child(id).child("Masv").setValue(inputMass);
                myRef.child(id).child("HoTen").setValue(inputHoTen);
                myRef.child(id).child("Email").setValue(inputEmail);
                myRef.child(id).child("SoDT").setValue(inputSoDT);
                finish();

            }
        });

    }

    private void init() {
        edtMass = findViewById(R.id.edt_mass);
        hoTen = findViewById(R.id.edt_ho_ten);
        email = findViewById(R.id.edt_email);
        soDt= findViewById(R.id.edt_sdt);
        btnHuy= findViewById(R.id.btn_huy);
        btnThem= findViewById(R.id.btn_them);
        btnBack= findViewById(R.id.btn_back);
    }

}
