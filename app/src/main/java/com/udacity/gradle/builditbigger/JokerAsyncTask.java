package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Mustafa Berkay Mutlu on 21.05.18.
 */
public class JokerAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "JokerAsyncTask";

    private MyApi myApiService;

    private final Callback callback;

    public interface Callback {
        void onResult(String joke);

        void onFailed();

        void onCancelled();
    }

    public JokerAsyncTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        final MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        myApiService = builder.build();
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return myApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (TextUtils.isEmpty(result)) {
            callback.onFailed();
        } else {
            callback.onResult(result);
        }
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);

        callback.onCancelled();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        callback.onCancelled();
    }
}