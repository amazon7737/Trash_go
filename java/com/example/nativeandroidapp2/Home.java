package com.example.nativeandroidapp2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Home extends AppCompatActivity {

    private static final int REQUEST_CODE = 22;


    TextView jsonView;
    TextView jsonView2;
    TextView jsonView3;
    TextView jsonView4;
    TextView jsonView5;


    Button btnpicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("메인 홈");

        // 게임 시작 Button
        Button gamebutton = findViewById(R.id.game_Start);
        gamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UnityHandlerActivity.class);
                startActivity(intent);
            }

//            Button buttonClick = findViewById(R.id.buttonClick);
//
//        buttonClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(UnityHandlerActivity.this, UnityPlayerActivity.class);
//                startActivity(intent);
//
//
//            }
        });

        // 보관함 Button
        Button basketbutton = findViewById(R.id.baskeIcon);
        basketbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), basket_page.class);
                startActivity(intent);
            }
        });

        // 분리수거 백과사전 Button
        Button bookbutton = findViewById(R.id.bookIcon);
        bookbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_paper_1.class);
                startActivity(intent);
            }
        });

        // Main Home Icon Button
        Button homebutton = findViewById(R.id.homeIcon);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        // My Information Icon Button
        Button informationbutton = findViewById(R.id.myIcon);

        informationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), information_page.class);
                startActivity(intent);
                new information_page.JSONTask().execute("http://192.168.0.165:7878");


            }

        });


        // ---------- 카메라 --------------
        btnpicture = findViewById(R.id.btncamera_id);

        btnpicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE);
            }
        });


        // ---------- json ----------------





    }

}