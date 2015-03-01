package net.kuwalab.android.cleanhakusan.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import net.kuwalab.android.cleanhakusan.AppOpenHelper;
import net.kuwalab.android.cleanhakusan.dao.impl.ChouDaoImpl;
import net.kuwalab.android.cleanhakusan.entity.Chou;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class ChouDaoTest {
    private AppOpenHelper helper;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        helper = new AppOpenHelper(new RenamingDelegatingContext(context, "test_"));
    }

    @After
    public void tearDown() {
        helper.close();
    }

    private void prepareData(SQLiteDatabase db) {
        String sql = "INSERT INTO chou(chou_name, trash_no) VALUES(?, ?)";

        String[][] params = {{"ほげ町", "1"}, {"ふう町", "2"}};
        for (String[] param : params) {
            db.execSQL(sql, param);
        }
    }

    @Test
    public void データの登録のテスト() throws Exception {
        SQLiteDatabase db = helper.getWritableDatabase();

        prepareData(db);

        Chou chou = new Chou();
        chou.setChouName("テスト町");
        chou.setTrashNo(10);

        ChouDao chouDao = new ChouDaoImpl(db);
        chouDao.insert(chou);

        Cursor cursor = db.rawQuery("SELECT * FROM chou WHERE chou_name='テスト町'", null);
        if (!cursor.moveToNext()) {
            fail("追加したレコードが存在しない");
        }

        assertThat("IDはインクリメントされている", cursor.getInt(0), is(3));
        assertThat("町名が登録されている", cursor.getString(1), is("テスト町"));
        assertThat("ゴミ回収番号が登録されている", cursor.getInt(2), is(10));

        db.close();
    }

    @Test
    public void カウントが正しく動作すること() throws Exception {
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            ChouDao chouDao = new ChouDaoImpl(db);

            assertThat("カウントの結果が0件になること", chouDao.count(), is(0L));

            prepareData(db);

            assertThat("カウントの結果が2件になること", chouDao.count(), is(2L));
        } finally {
            db.close();
        }
    }
}
