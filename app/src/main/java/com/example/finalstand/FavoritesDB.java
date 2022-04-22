package com.example.finalstand;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavoritesDB extends SQLiteOpenHelper {

    private static final String ID_COL = "id";
    private String TABLE_NAME = "drivers_info";

    private static final String NAME_COL = "name";
    private static final String DESC_COL = "description";

    public FavoritesDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public FavoritesDB(Context context, String databaseName) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_COL + " TEXT,"
                + DESC_COL + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    public void deleteInfo(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NAME_COL + "=" + "'" + name + "'", null);
        db.close();
    }

    public void addInfo(String name, String desc) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(NAME_COL, name);
        values.put(DESC_COL, desc);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkContains(String dbField, String fieldValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + dbField + " = " + "'" + fieldValue + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public ArrayList<String[]> readAll() {
        ArrayList<String[]> rows = new ArrayList<String[]>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
            rows.add(new String[] {cur.getString(cur.getColumnIndexOrThrow(NAME_COL)), cur.getString(cur.getColumnIndexOrThrow(DESC_COL))});
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();
        return rows;
    }

    public String selectQuery(String fieldName) {
        String query="SELECT " + fieldName + " FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract=db.rawQuery(query,null);
        extract.moveToLast();
        db.close();
        return extract.getString(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
