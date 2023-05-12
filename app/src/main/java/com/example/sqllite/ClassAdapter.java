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

import org.w3c.dom.Text;

public class ClassAdapter extends ArrayAdapter<ClassModel> {
    private Context context;

    private final int resourceLayout;

    SchoolDB schoolDB;
    public ClassAdapter(Context context, int resource, ClassModel[] classes)
    {
        super(context, resource, classes);
        this.context = context;
        this.resourceLayout = resource;
        schoolDB = new SchoolDB(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resourceLayout, null);
        }

        ClassModel classModel = getItem(position);
        TextView tvClassId = (TextView) view.findViewById(R.id.class_id);
        TextView tvClassName = (TextView) view.findViewById(R.id.class_name);
        TextView tvNumOfStudents = (TextView) view.findViewById(R.id.text_num_of_students);
        TextView tvStt = (TextView) view.findViewById(R.id.stt);

        tvClassId.setText(classModel.getId().toString());
        tvClassName.setText(classModel.getName().toString());
        tvNumOfStudents.setText(String.valueOf(CountStudents(classModel.getId())));
        tvStt.setText("#" + String.valueOf(classModel.getStt()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailClassPage.class);
                intent.putExtra("classModel", classModel);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
    private int CountStudents(String classId)
    {
        return schoolDB.loadAllStudents(classId).size();
    }
}
