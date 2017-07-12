package com.app3.imsaiful.saif_log;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by imsaiful on 9/7/17.
 */

public class BackgroundWorker extends AsyncTask<String,Void , String>{

    Context context;
    AlertDialog alertDialog;
    String result="";
    BackgroundWorker(Context ctx)
    {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params)
    {
        String type=params[0];
        String user_name=params[1];
        String user_pas=params[2];
        String log_in="http://shortinfo.info/saifulData/login.php";
        if(type.equals("login"))
        {
            try {
                URL url=new URL(log_in);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pas","UTF-8")+"="+URLEncoder.encode(user_pas,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else
        {
          result="Not Connected";
        }

        return null;
    }
    @Override
    protected void onPreExecute()
    {
       alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Log In Status");

    }

    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
