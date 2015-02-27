package net.kuwalab.android.cleanhakusan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class VersionAsyncTask extends AsyncTask<Void, Void, JSONObject> {
    private Context context;
    private AsyncTaskListener<Void, JSONObject> asyncTasklistener;
    private RequestQueue requestQueue;

    private ProgressDialog waitDialog;


    public VersionAsyncTask(Context context, AsyncTaskListener<Void, JSONObject> asynctaskListener,
                            RequestQueue requestQueue) {
        this.context = context;
        this.asyncTasklistener = asynctaskListener;
        this.requestQueue = requestQueue;
    }

    @Override
    protected void onPreExecute() {
        waitDialog = new ProgressDialog(context);
        // プログレスダイアログのメッセージを設定します
        waitDialog.setMessage("最新データ確認中");
        // 円スタイル（くるくる回るタイプ）に設定します
        waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // プログレスダイアログを表示
        waitDialog.show();

        asyncTasklistener.onStartBackgroundTask();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "https://cleanhakusan.herokuapp.com/api/version";

        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, future, future);
        requestQueue.add(request);
        requestQueue.start();
        JSONObject jsonObject = null;
        try {
            jsonObject = future.get(20_000, TimeUnit.MILLISECONDS);
            Log.i("##################", jsonObject.getString("version"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        waitDialog.dismiss();
        asyncTasklistener.onEndBackgroundTask(jsonObject);
    }
}
