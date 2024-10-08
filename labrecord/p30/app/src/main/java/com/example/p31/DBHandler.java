package com.example.p31;



//import the following
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;

public class DBHandler extends SQLiteOpenHelper {

    //creating constant variables that stores database details

    //database name
    private static final String DB_NAME = "student_db";

    //database version
    private static final int DB_VERSION=1;

    //tablename
    private static final String TABLE_NAME="student_details";

    //column names of the table: Strings
    //column 1: ID
    private static final String ID = "id";
    //column 2: NAME
    private static final String NAME= "name";
    //column 3: COURSE
    private static final String COURSE = "course";
    //column 4: SEMESTER
    private static final String SEMESTER = "semester";

    //constructor
    public DBHandler(Context context)
    {

        super(context,DB_NAME,null,DB_VERSION);
    }

    //create a database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +TABLE_NAME+ "("
                +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+ " TEXT,"
                +COURSE+ " TEXT," +SEMESTER+ " INTEGER)";
        //execute above query
        db.execSQL(query);

        //"CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" TEXT,"+COURSE+" TEXT,"+SEMESTER+" INTEGER)";
    }

    public void addNewStudent(String name, String course, int semester)
    {
//creating a variable for ur sqlite database and calling writable method
// as we are writing data in our database.
        SQLiteDatabase db=this.getWritableDatabase();
        //create a variable for content values
        ContentValues values=new ContentValues();

        //passing key-value pairs
        values.put(NAME,name);
        values.put(COURSE,course);
        values.put(SEMESTER,semester);

        //pass content values to our table.
        db.insert(TABLE_NAME,null,values);
        //close db
        db.close();
    }
    //returns the first matching row
    public Student findStudent(String studentname) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+TABLE_NAME+" where "+NAME+" = ?", new String[]{studentname});

        Student student=new Student();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setCourse(cursor.getString(2));
            student.setSemester(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }
    //delete a student
    public boolean deleteStudent(String studentname) {

        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+TABLE_NAME+" where "+NAME+" = ?", new String[]{studentname});

        Student student=new Student();

        if (cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, ID + " = ?",
                    new String[] { String.valueOf(student.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    //update student record
    public void updateStudent(String name,String course,int semester) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,name);
        values.put(COURSE,course);
        values.put(SEMESTER,semester);
        db.update(TABLE_NAME, values, NAME + " = ?", new String[]{name});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

