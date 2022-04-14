package com.example.finalstand;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavoritesDB extends SQLiteOpenHelper {

    private static final String ID_COL = "id";
    private String TABLE_NAME = "constructors_info";

    private static final String NAME_COL = "name";

    private static final String DESC_Col = "description";

    public FavoritesDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
