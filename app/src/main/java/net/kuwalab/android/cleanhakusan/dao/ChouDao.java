package net.kuwalab.android.cleanhakusan.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ChouDao {
    private SQLiteDatabase db;

    public ChouDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long count() {
        String sql = "SELECT COUNT(*) FROM chou";
        Cursor c = db.rawQuery(sql, null);
        c.moveToLast();
        long count = c.getLong(0);
        c.close();
        return count;
    }
}
