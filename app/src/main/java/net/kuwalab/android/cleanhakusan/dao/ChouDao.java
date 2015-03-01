package net.kuwalab.android.cleanhakusan.dao;

import net.kuwalab.android.cleanhakusan.entity.Chou;

import java.util.List;

public interface ChouDao {
    public void insert(Chou chou);

    public long count();

    public List<Chou> selectAll();
}
