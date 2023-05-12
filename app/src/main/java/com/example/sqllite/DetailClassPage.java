package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class DetailClassPage extends AppCompatActivity {

    ListView lvStudents;
    SchoolDB schoolDB;
    List<StudentModel> listStudents;

    TextView tvClassId;
    TextView tvClassName;
    TextView tvNumOfStudents;

    StudentModel[] studentArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_class_page);

        schoolDB = new SchoolDB(this);

        ClassModel classModel = (ClassModel) getIntent().getSerializableExtra("classModel");

        if(classModel != null)
        {
            tvClassId = (TextView) findViewById(R.id.class_id);
            tvClassName = (TextView) findViewById(R.id.class_name);
            tvNumOfStudents = (TextView) findViewById(R.id.text_num_of_students);

            tvClassId.setText(classModel.getId().toString());
            tvClassName.setText(classModel.getName().toString());
            tvNumOfStudents.setText(String.valueOf(schoolDB.loadAllStudents(classModel.getId()).size()));

            listStudents = schoolDB.loadAllStudents(classModel.getId());
            studentArray = listStudents.toArray(new StudentModel[0]);

            lvStudents = (ListView) findViewById(R.id.listview_students);
            lvStudents.setAdapter(new StudentAdapter(this, R.layout.detail_listview_item, studentArray));
        }



    }
}