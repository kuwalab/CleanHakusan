package net.kuwalab.android.cleanhakusan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import net.kuwalab.android.cleanhakusan.AppOpenHelper;
import net.kuwalab.android.cleanhakusan.dao.impl.TrashDaoImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class TrashDaoTest {
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
        String sql = "INSERT INTO trash(trash_no, trash_type, year, month, date) VALUES(?, ?, ?, ?, ?)";

        String[][] params = {
            {"1", "1", "2015", "3", "2"},
            {"1", "1", "2015", "3", "6"},
            {"1", "2", "2015", "3", "2"},
            {"1", "3", "2015", "3", "28"},
            {"1", "4", "2015", "3", "11"},
            {"2", "1", "2015", "3", "1"},
            {"2", "2", "2015", "3", "15"}
        };
        for (String[] param : params) {
            db.execSQL(sql, param);
        }
    }

    @Test
    public void カウントが正しく動作すること() throws Exception {
        TrashDao trashDao = new TrashDaoImpl(db);

        assertThat("カウントの結果が0件になること", trashDao.count(), is(0L));

        prepareData();

        assertThat("カウントの結果が7件になること", trashDao.count(), is(7L));
    }
}
