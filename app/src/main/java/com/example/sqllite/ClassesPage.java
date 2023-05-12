package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ClassesPage extends AppCompatActivity {

    ListView lvClasses;
    SchoolDB schoolDB;

    List<ClassModel> listClasses;

    ClassModel[] classArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes_page);

        schoolDB = new SchoolDB(this);

        listClasses = schoolDB.loadAllClasses();
        ResetStt(listClasses);
        classArray = listClasses.toArray(new ClassModel[0]);

        lvClasses = (ListView) findViewById(R.id.listview_classes);
        lvClasses.setAdapter(new ClassAdapter(this, R.layout.listview_item, classArray));
    }


    private void ResetStt(List<ClassModel> list)
    {
        int i = 1;
        for(ClassModel classModel : list)
        {
            classModel.setStt(i);
            i+=1;
        }
    }
}