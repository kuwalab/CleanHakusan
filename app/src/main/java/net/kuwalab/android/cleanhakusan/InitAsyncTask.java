package net.kuwalab.android.cleanhakusan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InitAsyncTask extends AsyncTask<Void, Integer, TrashInfo> {
    private Context context;
    private AsyncTaskListener<Void, TrashInfo> asyncTasklistener;
    private SyncJsonRequest syncJsonRequest;

    private ProgressDialog progressDialog;

    /** バージョンが取得できない場合 */
    private static final int VERSION_NOTHING = -1;

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
        int progress = 0;

        JSONObject jsonVersion = syncJsonRequest.getJson("http://cleanhakusan.herokuapp.com/api/version");
        int serverVersion = VERSION_NOTHING;
        try {
            if (jsonVersion != null) {
                try {
                    serverVersion = Integer.parseInt(jsonVersion.getString("version"));
                } catch (NumberFormatException e) {
                    // 何もしない
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progress++;
        publishProgress(progress);

        SharedPreferences sharedPreferences = context.getSharedPreferences("CLEAN_HAKUSAN", Context.MODE_PRIVATE);
        int currentVersion = sharedPreferences.getInt("version", VERSION_NOTHING);

        JSONObject trashList = syncJsonRequest.getJson("http://cleanhakusan.herokuapp.com/api/chouList");

        try {
            if (trashList != null) {
                JSONArray jsonArray = trashList.getJSONArray("chouList");
                Log.i("JSONARRAY", jsonArray.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
