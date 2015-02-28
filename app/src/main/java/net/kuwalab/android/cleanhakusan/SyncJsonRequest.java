package net.kuwalab.android.cleanhakusan;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncJsonRequest {
    private RequestQueue requestQueue;

    public SyncJsonRequest(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public JSONObject getJson(String url) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, future, future);
        requestQueue.add(request);
        requestQueue.start();
        JSONObject jsonObject = null;
        try {
            jsonObject = future.get(20_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
