package net.kuwalab.android.cleanhakusan.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.kuwalab.android.cleanhakusan.dao.ChouDao;
import net.kuwalab.android.cleanhakusan.entity.Chou;

import java.util.ArrayList;
import java.util.List;

public class ChouDaoImpl implements ChouDao {
    private SQLiteDatabase db;

    public ChouDaoImpl(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(@NonNull Chou chou) {
        ContentValues values = new ContentValues();
        values.put("chou_name", chou.getChouName());
        values.put("trash_no", chou.getTrashNo());

        db.insert("chou", "", values);
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM chou";
        Cursor c = db.rawQuery(sql, null);
        c.moveToLast();
        long count = c.getLong(0);
        c.close();
        return count;
    }

    @Override
    public void deleteAll() {
        db.delete("chou", null, null);
    }

    @Override
    @NonNull
    public List<Chou> selectAll() {
        List<Chou> chouList = new ArrayList<>();

        String sql = "SELECT _id,chou_name,trash_no FROM chou";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            chouList.add(toChou(cursor));
        }

        return chouList;
    }

    private Chou toChou(Cursor cursor) {
        Chou chou = new Chou();
        chou.setId(cursor.getInt(0));
        chou.setChouName(cursor.getString(1));
        chou.setTrashNo(cursor.getInt(2));
        return chou;
    }
}
