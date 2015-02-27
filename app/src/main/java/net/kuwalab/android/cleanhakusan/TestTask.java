package net.kuwalab.android.cleanhakusan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestTask extends AsyncTask<Void, Void, Void> {
    private ProgressDialog waitDialog;
    private Context context;

    private RequestQueue requestQueue;


    public TestTask(Context context) {
        this.context = context;
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
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.i("####", "start");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("####", "start");
        String url = "https://cleanhakusan.herokuapp.com/api/version";

        requestQueue = Volley.newRequestQueue(context);

        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, future, future);
        requestQueue.add(request);
        requestQueue.start();
        try {
            JSONObject result = future.get(20_000, TimeUnit.MILLISECONDS);
            Log.i("$$$$$$$$$$", result.toString());
            try {
                Log.i("###########", result.getString("version"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        waitDialog.dismiss();
        return null;
    }
}
