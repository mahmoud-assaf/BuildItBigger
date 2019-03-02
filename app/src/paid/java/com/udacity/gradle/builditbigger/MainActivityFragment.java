package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.androidjokeslibrary.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.jokes.Joker;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
TextView jokeTxtView;
Joker myJoker;
Button showJoke;
public ProgressBar bar;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


         myJoker = new Joker();
        jokeTxtView=root.findViewById(R.id.instructions_text_view);
        bar=root.findViewById(R.id.progressBar);
        showJoke=root.findViewById(R.id.showJoke);
        showJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyEndPointAsyncTask().execute(new Pair<Context, String>(getActivity(), ""));
            //    jokeTxtView.setText(myJoker.getJoke());
               /* Intent showJokeActivity=new Intent(getActivity(), DisplayJokeActivity.class);
                showJokeActivity.putExtra("joke","to do getting from the java library .... funny joke hehehee");
                startActivity(showJokeActivity);*/
            }
        });
        return root;
    }



    public class MyEndPointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private  MyApi myApiService = null;
        private Context context;
        public  String asyncTaskResult="error";
        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                return myApiService.getNiceJoke().execute().getData();
            } catch (IOException e) {
                //return e.getMessage();
                return "error";
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            bar.setVisibility(View.INVISIBLE);
            if(!result.equals("error")) {
                //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                asyncTaskResult=result;
                Intent showJokeActivity = new Intent(context, DisplayJokeActivity.class);
                showJokeActivity.putExtra("joke", result);
                context.startActivity(showJokeActivity);
            }else{
                Toast.makeText(context, "something went wrong", Toast.LENGTH_LONG).show();

            }

        }
    }

}
