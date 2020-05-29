package com.example.qlsvfirebase.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsvfirebase.R;
import com.example.qlsvfirebase.activity.ActivityEdit;
import com.example.qlsvfirebase.activity.ActivityInput;
import com.example.qlsvfirebase.object.Student;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Student> studentArrayList;
    private Context activity;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Student> students, Context activity) {
        this.studentArrayList = students;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_inform,parent,false);

        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Student student = studentArrayList.get(position);

        holder.txtHoTen.setText(student.getHoTen());
        holder.txtEmail.setText(student.getEmail());
        holder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(activity,v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.add_student){
                            Toast.makeText(activity, "Ban chon thêm sv", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(activity, ActivityInput.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                        }else if (item.getItemId() == R.id.edit_inform){
                            Toast.makeText(activity, "Ban chon sửa sv", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(activity, ActivityEdit.class);
                            intent.putExtra("SINHVIEN", student);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                        }else if (item.getItemId() == R.id.delete_inform){
                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                            final DatabaseReference myRef = database.getReference("DbStudent");
                            myRef.child(student.getId()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    Toast.makeText(activity, "Ban xóa thanh công", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHoTen;
        public TextView txtEmail;
        public ImageView btnMenu;

        public MyViewHolder(View v) {
            super(v);
            txtHoTen = v.findViewById(R.id.txt_hoten);
            txtEmail = v.findViewById(R.id.txt_email);
            btnMenu = v.findViewById(R.id.btn_menu);
        }
    }


}