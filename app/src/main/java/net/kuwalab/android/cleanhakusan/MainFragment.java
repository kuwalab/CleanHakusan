package net.kuwalab.android.cleanhakusan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainFragment extends Fragment {
    private RequestQueue requestQueue;
    private TextView versionTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        versionTextView = (TextView) view.findViewById(R.id.versionTextView);

        requestQueue = Volley.newRequestQueue(getActivity());

        versionCheck();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void versionCheck() {
        JsonAsyncTask jsonAsyncTask = new JsonAsyncTask(getActivity(), new AsyncTaskListener<Void, JSONObject>() {
            @Override
            public void onStartBackgroundTask() {}

            @Override
            public void onProgressUpdate(Void progress) {}

            @Override
            public void onEndBackgroundTask(JSONObject result) {
                try {
                    if (result == null) {
                        versionTextView.setText("ぬるぬる");
                    } else {
                        versionTextView.setText(result.getString("version"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelledTask() {}
        }, new SyncJsonRequest(requestQueue, "http://cleanhakusan.herokuapp.com/api/version"));
        jsonAsyncTask.execute();
    }
}
