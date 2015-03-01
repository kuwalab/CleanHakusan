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

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class ChouDaoTest {
    private AppOpenHelper helper;
    private SQLiteDatabase db;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        helper = new AppOpenHelper(new RenamingDelegatingContext(context, "test_"));

        db = helper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        db.close();
        helper.close();
    }

    private void prepareData() {
        String sql = "INSERT INTO chou(chou_name,trash_no) VALUES(?,?)";

        String[][] params = {{"ほげ町", "3"}, {"ふう町", "5"}};
        for (String[] param : params) {
            db.execSQL(sql, param);
        }
    }

    @Test
    public void データの登録のテスト() throws Exception {
        prepareData();

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
    }

    @Test
    public void カウントが正しく動作すること() throws Exception {
        ChouDao chouDao = new ChouDaoImpl(db);

        assertThat("カウントの結果が0件になること", chouDao.count(), is(0L));

        prepareData();

        assertThat("カウントの結果が2件になること", chouDao.count(), is(2L));
    }

    @Test
    public void 全件取得が正しく動作すること() throws Exception {
        prepareData();

        ChouDao chouDao = new ChouDaoImpl(db);
        List<Chou> chouList = chouDao.selectAll();
        assertThat("検索結果の数が正しいこと", chouList.size(), is(2));

        Chou chou1 = chouList.get(0);
        assertThat(chou1.getId(), is(1));
        assertThat(chou1.getChouName(), is("ほげ町"));
        assertThat(chou1.getTrashNo(), is(3));

        Chou chou2 = chouList.get(1);
        assertThat(chou2.getId(), is(2));
        assertThat(chou2.getChouName(), is("ふう町"));
        assertThat(chou2.getTrashNo(), is(5));
    }
}
