package com.example.ikool009.call_api;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ikool009 on 27/1/2561.
 */

public class Call_login extends AsyncTask<String,Void,String> {
    private String username, password;

    public Call_login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            Log.d("test_log",strings[0]);
            FormBody.Builder formBody = new FormBody.Builder();
            final String url = strings[0];
            formBody.add("user",username);
            formBody.add("pass", password);



            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(url).post(formBody.build()).build();

            Response response = okHttpClient.newCall(request).execute();

            if(response.code() == 200){

                return response.body().string();
            }else{

            }

        }catch (Exception e){

        }

        return null;
    }
}
