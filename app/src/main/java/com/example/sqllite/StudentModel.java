package com.example.sqllite;

import java.util.UUID;

public class StudentModel
{
    private String classId;
    private String id;
    private String name;
    private String dob;

    public StudentModel(String classId, String id, String name, String dob)
    {
        this.classId = classId;
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
