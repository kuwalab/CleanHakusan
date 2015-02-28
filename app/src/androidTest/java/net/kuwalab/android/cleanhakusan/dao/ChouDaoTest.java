package net.kuwalab.android.cleanhakusan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import net.kuwalab.android.cleanhakusan.AppOpenHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ChouDaoTest {
    private AppOpenHelper helper;

    private Context context;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        helper = new AppOpenHelper(new RenamingDelegatingContext(context, "test_"));
    }

    @After
    public void tearDown() {
        helper.close();
    }

    @Test
    public void count() throws Exception {
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            ChouDao chouDao = new ChouDao(db);

            assertThat(chouDao.count(), is(0L));
        } finally {
            db.close();
        }
    }
}
