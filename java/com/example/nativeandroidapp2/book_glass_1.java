package com.example.nativeandroidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class book_glass_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_glass1);
        setTitle("분리수거 백과사전[유리]");

        // Main Home Button
        Button homebutton = findViewById(R.id.homeIcon);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        // paperIcon Click
        Button paperPage = findViewById(R.id.paperIcon);
        paperPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_paper_1.class);
                startActivity(intent);
            }
        });

        // plasticIcon Click
        Button plasticPage = findViewById(R.id.plasticIcon);
        plasticPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_plastic_1.class);
                startActivity(intent);
            }
        });

        // vinylIcon Click
        Button vinylPage = findViewById(R.id.vinylIcon);
        vinylPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_vinyl_1.class);
                startActivity(intent);
            }
        });

        // glasslIcon Click
        Button glassPage = findViewById(R.id.glassIcon);
        glassPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_glass_1.class);
                startActivity(intent);
            }
        });

        // scrapmetalIcon Click
        Button scrapmetalPage = findViewById(R.id.scapmetalIcon);
        scrapmetalPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_scrapmetal_1.class);
                startActivity(intent);
            }
        });

        // My Information Icon Button
        Button informationbutton = findViewById(R.id.myIcon);
        informationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), information_page.class);
                startActivity(intent);
            }
        });
    }
}