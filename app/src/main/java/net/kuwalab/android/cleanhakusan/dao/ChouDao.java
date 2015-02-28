package net.kuwalab.android.cleanhakusan.dao;

import android.database.sqlite.SQLiteDatabase;

public class ChouDao {
    private SQLiteDatabase db;

    public ChouDao(SQLiteDatabase db) {
        this.db = db;
    }

    public int count() {
        return 0;
    }
}
