package com.example.qlsvfirebase.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsvfirebase.R;
import com.example.qlsvfirebase.object.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityInput extends AppCompatActivity {
    private EditText edtMass;
    private EditText hoTen;
    private EditText email;
    private EditText soDt;
    private Button btnHuy, btnBack, btnThem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        init();
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
                final Student student = new Student(inputMass,inputHoTen,inputEmail,inputSoDT);

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("DbStudent");
                // Read from the database
                // tạo 1 id ngẫu nhiên trên firebase
                String id = myRef.push().getKey();
                myRef.child(id).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công" , Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại" , Toast.LENGTH_LONG).show();
                    }
                });

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
