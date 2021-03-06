package net.kuwalab.android.cleanhakusan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MainFragment extends Fragment {
    private RequestQueue requestQueue;
    private AppCompatTextView versionTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        versionTextView = (AppCompatTextView) view.findViewById(R.id.versionTextView);

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
        InitAsyncTask jsonAsyncTask = new InitAsyncTask(getActivity(), new AsyncTaskListener<Void, TrashInfo>() {
            @Override
            public void onStartBackgroundTask() {
            }

            @Override
            public void onProgressUpdate(Void progress) {
            }

            @Override
            public void onEndBackgroundTask(TrashInfo result) {
            }

            @Override
            public void onCancelledTask() {
            }
        }, new SyncJsonRequest(requestQueue));
        jsonAsyncTask.execute();
    }
}
