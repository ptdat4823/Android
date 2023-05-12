package com.example.sqllite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentAdapter extends ArrayAdapter<StudentModel> {
    private Context context;

    private final int resourceLayout;


    public StudentAdapter(Context context, int resource, StudentModel[] classes)
    {
        super(context, resource, classes);
        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resourceLayout, null);
        }

        StudentModel studentModel = getItem(position);
        TextView tvStudentId = (TextView) view.findViewById(R.id.student_id);
        TextView tvStudentName = (TextView) view.findViewById(R.id.student_name);
        TextView tvStudentDob = (TextView) view.findViewById(R.id.student_dob);

        tvStudentId.setText(studentModel.getId());
        tvStudentName.setText(studentModel.getName());
        tvStudentDob.setText(studentModel.getDob());

        return view;
    }
}
