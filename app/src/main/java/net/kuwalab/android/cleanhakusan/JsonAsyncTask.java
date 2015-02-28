package net.kuwalab.android.cleanhakusan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

public class JsonAsyncTask extends AsyncTask<Void, Void, JSONObject> {
    private Context context;
    private AsyncTaskListener<Void, JSONObject> asyncTasklistener;
    private SyncJsonRequest syncJsonRequest;

    private ProgressDialog waitDialog;


    public JsonAsyncTask(Context context, AsyncTaskListener<Void, JSONObject> asynctaskListener,
                         SyncJsonRequest syncJsonRequest) {
        this.context = context;
        this.asyncTasklistener = asynctaskListener;
        this.syncJsonRequest = syncJsonRequest;
    }

    @Override
    protected void onPreExecute() {
        waitDialog = new ProgressDialog(context);
        // プログレスダイアログのメッセージを設定します
        waitDialog.setMessage(context.getResources().getString(R.string.task_doing));
        // 円スタイル（くるくる回るタイプ）に設定します
        waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // プログレスダイアログを表示
        waitDialog.show();

        asyncTasklistener.onStartBackgroundTask();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        return syncJsonRequest.getJson();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        waitDialog.dismiss();
        asyncTasklistener.onEndBackgroundTask(jsonObject);
    }
}
