package com.example.sqlitecursoradapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UsersDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "STDTS_DB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STDTS = "students";

    public static final String STDT_ID = "_id";
    public static final String STDT_NAME = "userName";
    public static final String STDT_AGE = "age";

    public UsersDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_STDTS +
                "(" +
                STDT_ID + " INTEGER PRIMARY KEY," +
                STDT_NAME + " TEXT," +
                STDT_AGE + " TEXT" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STDTS);
        onCreate(db);
    }
}