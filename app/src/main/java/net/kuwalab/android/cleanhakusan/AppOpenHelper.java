package net.kuwalab.android.cleanhakusan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "app";

    private static final int LATEST_VERSION = 1;

    public AppOpenHelper(Context context) {
        this(context, LATEST_VERSION);
    }

    public AppOpenHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CHOU(_id INTEGER PRIMARY KEY AUTOINCREMENT, chou_name TEXT, trash_no INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table user");

        onCreate(db);
    }
}
