package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {

    Button btnLogin;
    EditText inputEmail;
    EditText inputPassword;

    SchoolDB schoolDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        btnLogin = (Button) findViewById(R.id.btn_login);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        schoolDB = new SchoolDB(this);

        LoadDB();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                UserModel user = schoolDB.getByEmail(email);
                if(user != null)
                {
                    if(password.equals(user.getPassword()))
                    {
                        Intent intent = new Intent(login_page.this, ClassesPage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(login_page.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(login_page.this, "User not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void LoadDB()
    {
        if(schoolDB.loadAllUsers().size() == 0)
        {
            schoolDB.addNewUser(new UserModel("abc", "123"));
        }
        if(schoolDB.loadAllClasses().size() == 0)
        {
            ClassModel classModel1 = new ClassModel(getNextClassId(), "tu tuong hcm");
            schoolDB.addNewClass(classModel1);
            ClassModel classModel2 = new ClassModel(getNextClassId(), "NMCNPM");
            schoolDB.addNewClass(classModel2);
            ClassModel classModel3 = new ClassModel(getNextClassId(), "LSD");
            schoolDB.addNewClass(classModel3);
            ClassModel classModel4 = new ClassModel(getNextClassId(), "KTCT");
            schoolDB.addNewClass(classModel4);
        }
        for(ClassModel classModel : schoolDB.loadAllClasses())
        {
            if(schoolDB.loadAllStudents(classModel.getId()).size() == 0)
            {
                String classId = classModel.getId();
                StudentModel studentModel1 = new StudentModel(classId,classId + "_" + getNextStudentId(classId), "Nguyen Van A", "1/1/2003");
                schoolDB.addNewStudent(studentModel1);
                StudentModel studentModel2 = new StudentModel(classId,classId + "_" + getNextStudentId(classId), "Nguyen Van B", "1/1/2003");
                schoolDB.addNewStudent(studentModel2);
                StudentModel studentModel3 = new StudentModel(classId,classId + "_" + getNextStudentId(classId), "Nguyen Van C", "1/1/2003");
                schoolDB.addNewStudent(studentModel3);
            }
        }
    }

    public void deleteDB()
    {
        this.deleteDatabase(schoolDB.getDatabaseName());
    }
    private String getNextClassId()
    {
        if(schoolDB.loadAllClasses() == null)
            return "";
        int numOfClass = schoolDB.loadAllClasses().size();
        numOfClass += 1;
        return "class" + numOfClass;
    }
    private int CountStudents(String classId)
    {
        return schoolDB.loadAllStudents(classId).size();
    }
    private String getNextStudentId(String classId)
    {
        if(schoolDB.loadAllStudents(classId) == null)
            return "";
        int numOfStudent = schoolDB.loadAllStudents(classId).size();
        numOfStudent += 1;
        return "student" + numOfStudent;
    }
}