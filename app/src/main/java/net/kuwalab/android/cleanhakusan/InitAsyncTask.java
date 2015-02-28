package net.kuwalab.android.cleanhakusan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class InitAsyncTask extends AsyncTask<Void, Integer, TrashInfo> {
    private Context context;
    private AsyncTaskListener<Void, TrashInfo> asyncTasklistener;
    private SyncJsonRequest syncJsonRequest;

    private ProgressDialog progressDialog;

    public InitAsyncTask(Context context, AsyncTaskListener<Void, TrashInfo> asynctaskListener,
                         SyncJsonRequest syncJsonRequest) {
        this.context = context;
        this.asyncTasklistener = asynctaskListener;
        this.syncJsonRequest = syncJsonRequest;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(10);
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        asyncTasklistener.onStartBackgroundTask();
    }

    @Override
    protected TrashInfo doInBackground(Void... params) {
        int progress = 1;

        JSONObject jsonObject = syncJsonRequest.getJson("http://cleanhakusan.herokuapp.com/api/version");
        try {
            Log.i("#########", jsonObject.getString("version"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishProgress(progress);

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(TrashInfo trashInfo) {
        progressDialog.dismiss();
        asyncTasklistener.onEndBackgroundTask(trashInfo);
    }
}
