package com.example.nativeandroidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class information_page extends AppCompatActivity {

    static TextView jsonView;
    static TextView jsonView2;
    static TextView jsonView3;
    static TextView jsonView4;
    static TextView jsonView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);


        setTitle("내 정보");

        // Main Home Button
        Button homebutton = findViewById(R.id.homeIcon);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        // My Information Icon Button
        Button informationbutton = findViewById(R.id.myIcon2);
        jsonView = findViewById(R.id.jsonObj1);
        jsonView2 = findViewById(R.id.jsonObj2);
        jsonView3 = findViewById(R.id.jsonObj3);
        jsonView4 = findViewById(R.id.jsonObj4);
        jsonView5 = findViewById(R.id.jsonObj5);

        informationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONTask().execute("http://192.168.0.165:7878");

                System.out.println("!!!");

//                Intent intent = new Intent(getApplicationContext(), information_page.class);

//                startActivity(intent);
            }
        });



    }
    public static class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {

            HttpURLConnection con = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);

                con = (HttpURLConnection) url.openConnection();
                con.connect();

                con.setRequestMethod("GET");

                InputStream stream = con.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) buffer.append(line);

//                System.out.println(string_);
                String string_ = buffer.toString();
                JSONObject jsonobject = new JSONObject(string_);
//                System.out.println(jsonobject.get("일반쓰레기"));


//                (int) jsonobject.get("일반쓰레기");

                //   List<String> trash = new ArrayList<>();



                return String.valueOf(jsonobject);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } finally {
                con.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(String.valueOf(s));
            try {
                JSONObject jsonobject = new JSONObject(s);
//                System.out.println("!!!:"+jsonobject.get("일반쓰레기"));

                jsonView.setText((jsonobject.get("일반쓰레기").toString()));
                jsonView2.setText(jsonobject.get("종이쓰레기").toString());
                jsonView3.setText(jsonobject.get("플라스틱").toString());
                jsonView4.setText(jsonobject.get("고철").toString());
                jsonView5.setText(jsonobject.get("유리").toString());

            } catch (JSONException e) {
                System.out.println("!!!!!");
                throw new RuntimeException(e);
            }


//            JSONObject jsonObject = new JSONObject(s);
//            jsonView.setText(s.get("일반쓰레기"));

        }
    }


}