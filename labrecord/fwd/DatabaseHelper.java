package com.example.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BloodBank.db";
    private static final String TABLE_DONORS = "donors";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";
    private static final String COL_BLOOD_GROUP = "blood_group";
    private static final String COL_LOCATION = "location";
    private static final String COL_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_DONORS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_EMAIL + " TEXT UNIQUE, "
                + COL_PHONE + " TEXT, "
                + COL_BLOOD_GROUP + " TEXT, "
                + COL_LOCATION + " TEXT, "
                + COL_PASSWORD + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONORS);
        onCreate(db);
    }

    // Insert new donor
    public boolean insertDonor(String name, String email, String phone, String bloodGroup, String location, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_BLOOD_GROUP, bloodGroup);
        contentValues.put(COL_LOCATION, location);
        contentValues.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_DONORS, null, contentValues);
        return result != -1;
    }

    // Check if user exists (for login)
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DONORS + " WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    // Search donors
    public Cursor searchDonors(String bloodGroup, String location) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id as _id, name, phone FROM " + TABLE_DONORS + " WHERE blood_group = ? AND location = ?", new String[]{bloodGroup, location});
        if (cursor != null && cursor.moveToFirst()) {
            return cursor;
        }
        return null;
    }

    // Update donor profile
    public boolean updateDonor(int id, String name, String email, String phone, String bloodGroup, String location, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_BLOOD_GROUP, bloodGroup);
        contentValues.put(COL_LOCATION, location);
        contentValues.put(COL_PASSWORD, password);

        int result = db.update(TABLE_DONORS, contentValues, COL_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0; // Returns true if the update was successful
    }

    // Get donor by ID
    public Donor getDonorById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id as _id, name, phone FROM " + TABLE_DONORS + " WHERE " + COL_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            Donor donor = new Donor();
            donor.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
            donor.setName(cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)));
            donor.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)));
            donor.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE)));
            donor.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(COL_BLOOD_GROUP)));
            donor.setLocation(cursor.getString(cursor.getColumnIndexOrThrow(COL_LOCATION)));
            donor.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)));
            cursor.close();
            return donor;
        } else {
            return null; // Donor not found
        }
    }


}
