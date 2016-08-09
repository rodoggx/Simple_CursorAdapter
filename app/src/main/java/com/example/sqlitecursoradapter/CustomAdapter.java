package com.example.sqlitecursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomAdapter extends CursorAdapter {
    public CustomAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView) view.findViewById(R.id.lName);
        TextView textViewAge = (TextView) view.findViewById(R.id.lAge);

        String body = cursor.getString(cursor.getColumnIndexOrThrow(UsersDatabaseHelper.STDT_NAME));
        int age = cursor.getInt(cursor.getColumnIndexOrThrow(UsersDatabaseHelper.STDT_AGE));

        textViewName.setText(body);
        textViewAge.setText(String.valueOf(age));
    }
}