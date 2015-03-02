package net.kuwalab.android.cleanhakusan;

import android.support.test.runner.AndroidJUnit4;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.NoCache;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class SyncJsonRequestTest {

    @Test
    public void 通信の正常系テスト() {
        RequestQueue requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(
            new HttpStackJsonResponse()));

        SyncJsonRequest syncJsonRequest = new SyncJsonRequest(requestQueue);
        assertThat(syncJsonRequest.getJson("").toString(), is("{\"foo\":\"bar\"}"));
    }

    @Test
    public void test() {
        RequestQueue requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(
            new HttpStackServerError()));

        SyncJsonRequest syncJsonRequest = new SyncJsonRequest(requestQueue);
        assertThat(syncJsonRequest.getJson(""), is(nullValue()));
    }
}
