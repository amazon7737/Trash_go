package com.example.nativeandroidapp2;


import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.ContextWrapper;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.FileProvider;

        import java.io.DataOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 22;
    Button btnpicture;

    ImageView imageView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.Home);

        btnpicture = findViewById(R.id.btncamera_id);
//        imageView = findViewById(R.id.imageView1);

        btnpicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            System.out.println("하하하");


        }
        else{
            Toast.makeText(this, "Cancelled",Toast.LENGTH_SHORT ).show();
            super.onActivityResult(requestCode, resultCode, data);

            System.out.println("하하하2");
        }
    }

    private void uploadImage(File imageFile) {



    }


}
