package net.kuwalab.android.cleanhakusan.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.kuwalab.android.cleanhakusan.dao.TrashDao;

public class TrashDaoImpl implements TrashDao {
    private SQLiteDatabase db;

    public TrashDaoImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM trash";
        Cursor c = db.rawQuery(sql, null);
        c.moveToLast();
        long count = c.getLong(0);
        c.close();
        return count;
    }
}
