package com.spronghi.kiu.http;

import android.os.AsyncTask;
import android.util.Log;

import com.spronghi.kiu.setup.StringFormatter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

/**
 * Created by spronghi on 30/08/16.
 */
class HttpConnector {
    private static final String HOST_IP = "192.168.1.2";
    private static final String SERVER_ADDRESS = "http://"+HOST_IP+":8080/kiu";
    private static String url;

    public static String makeRequest(String service){
        try {
            url = StringFormatter.formatURL(SERVER_ADDRESS+service);
            Log.d("http", url);
            return StringFormatter.formatJSON(new HttpAsyncConnector().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "internal error";
    }
    private static class HttpAsyncConnector extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient myClient = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                HttpResponse response = myClient.execute(post);
                InputStreamReader stream = new InputStreamReader(response.getEntity().getContent());
                BufferedReader br = new BufferedReader(stream);

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line + "\n");

                br.close();
                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "internal error";
        }

    }
}
