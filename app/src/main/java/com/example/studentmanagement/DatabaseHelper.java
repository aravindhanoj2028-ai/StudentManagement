
package com.example.studentmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "StudentDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "roll TEXT, " +
                "course TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
    }

    public boolean addStudent(String name,
                              String roll, String course) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("roll", roll);
        values.put("course", course);

        return db.insert("Students", null, values) != -1;
    }

    public String getAllStudents() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Students", null);

        StringBuilder result = new StringBuilder();

        try {
            while (cursor.moveToNext()) {

                result.append("Name: ")
                        .append(cursor.getString(1))
                        .append("\nRoll No: ")
                        .append(cursor.getString(2))
                        .append("\nCourse: ")
                        .append(cursor.getString(3))
                        .append("\n-------------------\n");
            }
        } finally {
            cursor.close();
        }

        if (result.length() == 0) {
            return "No Students Found";
        }

        return result.toString();
    }
}
