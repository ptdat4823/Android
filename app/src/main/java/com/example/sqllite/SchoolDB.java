package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SchoolDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "School.db";

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_CLASS = "class";
    private static final String TABLE_STUDENT = "student";

    // user Table Columns
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_USER_PASSWORD = "password";

    // class Table Columns
    private static final String KEY_CLASS_ID = "classId";
    private static final String KEY_CLASS_NAME = "className";

    // student Table Columns
    private static final String KEY_STUDENT_ID = "studentId";
    private static final String KEY_STUDENT_NAME = "studentName";
    private static final String KEY_STUDENT_DOB = "studentDob";
    private static final String KEY_STUDENT_CLASSID = "studentClassId";

    public SchoolDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(" +
                KEY_USER_NAME + " TEXT PRIMARY KEY," + // Define a primary key
                KEY_USER_PASSWORD + " TEXT " +
                ")";
        String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_CLASS +
                "(" +
                KEY_CLASS_ID + " TEXT PRIMARY KEY," + // Define a primary key
                KEY_CLASS_NAME + " TEXT" +
                ")";
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT +
                "(" +
                KEY_STUDENT_ID + " TEXT PRIMARY KEY," + // Define a primary key
                KEY_STUDENT_NAME + " TEXT," +
                KEY_STUDENT_DOB + " TEXT, " +
                KEY_STUDENT_CLASSID + " TEXT " +
                ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CLASS_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
            onCreate(db);
        }
    }

    public void addNewUser(UserModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_USER_NAME, item.getUsername());
        values.put(this.KEY_USER_PASSWORD, item.getPassword());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addNewClass(ClassModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_CLASS_ID, item.getId());
        values.put(this.KEY_CLASS_NAME, item.getName());
        db.insert(TABLE_CLASS, null, values);
        db.close();
    }

    public void addNewStudent(StudentModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_STUDENT_ID, item.getId());
        values.put(this.KEY_STUDENT_NAME, item.getName());
        values.put(this.KEY_STUDENT_DOB, item.getDob());
        values.put(this.KEY_STUDENT_CLASSID, item.getClassId());
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public boolean updateUser(UserModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_USER_PASSWORD, item.getPassword());
        int rowAffected = db.update(TABLE_USER, values, KEY_USER_NAME + "= ?", new String[]{item.getUsername()});
        db.close();
        return rowAffected > 0;
    }

    public boolean updateClass(ClassModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_CLASS_NAME, item.getName());
        int rowAffected = db.update(TABLE_CLASS, values, KEY_CLASS_ID + "= ?", new String[]{item.getId()});
        db.close();
        return rowAffected > 0;
    }

    public boolean updateStudent(StudentModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_STUDENT_NAME, item.getName());
        values.put(this.KEY_STUDENT_DOB, item.getDob());
        values.put(this.KEY_STUDENT_CLASSID, item.getClassId());
        int rowAffected = db.update(TABLE_CLASS, values, KEY_STUDENT_ID + "= ?", new String[]{item.getId()});
        db.close();
        return rowAffected > 0;
    }

    public boolean deleteUser(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowAffected = db.delete(TABLE_USER, KEY_USER_NAME + "= ?", new String[]{username});
        db.close();
        return rowAffected > 0;
    }

    public ArrayList<UserModel> loadAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                KEY_USER_NAME, //TEXT
                KEY_USER_PASSWORD //TEXT
        };
        Cursor cursor = db.query(TABLE_USER, projection, null, null, null, null, null);
        ArrayList<UserModel> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            items.add(new UserModel(username,password));
        }
        db.close();
        return items;
    }

    public ArrayList<ClassModel> loadAllClasses() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                KEY_CLASS_ID,   //TEXT
                KEY_CLASS_NAME, //TEXT
        };
        Cursor cursor = db.query(TABLE_CLASS, projection, null, null, null, null, null);
        ArrayList<ClassModel> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String className = cursor.getString(1);
            items.add(new ClassModel(id,className));
        }
        db.close();
        return items;
    }

    public ArrayList<StudentModel> loadAllStudents(String classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                KEY_STUDENT_ID,   //TEXT
                KEY_STUDENT_NAME, //TEXT
                KEY_STUDENT_DOB, //TEXT
                KEY_STUDENT_CLASSID //TEXT
        };
        Cursor cursor = db.query(TABLE_STUDENT, projection, KEY_STUDENT_CLASSID + "= ?", new String[]{classId}, null,null,null, null);
        ArrayList<StudentModel> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String studentName = cursor.getString(1);
            String studentDob = cursor.getString(2);
            String studentClassId = cursor.getString(3);
            items.add(new StudentModel(studentClassId,id,studentName,studentDob));
        }
        db.close();
        return items;
    }

    public UserModel getByEmail(String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] projection = {
                KEY_USER_NAME,
                KEY_USER_PASSWORD
        };
        Cursor cursor = db.query(TABLE_USER, projection, KEY_USER_NAME + "= ?", new String[]{Email}, null,null,null, null);
        if(cursor.moveToFirst()){
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            db.close();
            return new UserModel(username, password);
        }
        return null;
    }
    public ClassModel getByClassId(String Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] projection = {
                KEY_CLASS_ID,   //TEXT
                KEY_CLASS_NAME, //TEXT
        };
        Cursor cursor = db.query(TABLE_CLASS, projection, KEY_CLASS_ID + "= ?", new String[]{Id}, null,null,null, null);
        ArrayList<ClassModel> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            String id = cursor.getString(0);
            String className = cursor.getString(1);
            db.close();
            return new ClassModel(id, className);
        }
        return null;
    }
    public StudentModel getByStudentId(String Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] projection = {
                KEY_CLASS_ID,   //TEXT
                KEY_CLASS_NAME, //TEXT
                KEY_STUDENT_DOB, //TEXT
                KEY_STUDENT_CLASSID //TEXT
        };
        Cursor cursor = db.query(TABLE_STUDENT, projection, KEY_STUDENT_ID + "= ?", new String[]{Id}, null,null,null, null);
        ArrayList<ClassModel> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String dob = cursor.getString(2);
            String classId = cursor.getString(3);
            db.close();
            return new StudentModel(classId,id,name, dob);
        }
        return null;
    }
}


