package com.example.sqlitecursoradapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    private static final String NAME = "Mike";
    private static final int AGE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fillDatabase(View view) {
        UsersDatabaseHelper usersDatabaseHelper = new UsersDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = usersDatabaseHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            for (int i = 0; i < 3; i++) {
                ContentValues values = new ContentValues();
                values.put(UsersDatabaseHelper.STDT_NAME, NAME + " " + i);
                values.put(UsersDatabaseHelper.STDT_AGE, AGE + i);

                db.insertOrThrow(UsersDatabaseHelper.TABLE_STDTS, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    public void logInformation(View view) {
        UsersDatabaseHelper usersDatabaseHelper = new UsersDatabaseHelper(getApplicationContext());

        final String POSTS_SELECT_QUERY = "SELECT * FROM students";

        SQLiteDatabase db = usersDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    String nameUser = cursor.getString(cursor.getColumnIndex(UsersDatabaseHelper.STDT_NAME));
                    String ageUser = cursor.getString(cursor.getColumnIndex(UsersDatabaseHelper.STDT_ID));

                    Log.d(TAG, "logInformation: " + nameUser);
                    Log.d(TAG, "logInformation: " + ageUser);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public void fillListView(View view) {
        UsersDatabaseHelper usersDatabaseHelper = new UsersDatabaseHelper(getApplicationContext());

        final String POSTS_SELECT_QUERY = "SELECT * FROM students";

        SQLiteDatabase db = usersDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

        ListView lvItems = (ListView) findViewById(R.id.a_main_listview);
        CustomAdapter todoAdapter = new CustomAdapter(this, cursor, 0);
        lvItems.setAdapter(todoAdapter);
    }
}
