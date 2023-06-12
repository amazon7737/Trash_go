package com.example.nativeandroidapp2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UnityHandlerActivity extends AppCompatActivity {
    String data1 = "일반쓰레기";
    String data2 = "종이쓰레기";
    String data3 = "플라스틱";
    String data4 = "고철";
    String data5 = "유리";

    public static String output_ = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_handler);

        Button buttonClick = findViewById(R.id.buttonClick);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnityHandlerActivity.this, UnityPlayerActivity.class);
                startActivity(intent);
                startLogMonitoring();
//                Log.d(TAG,"종이쓰레기");


            }
        });
    }

    // --------- log 감시 모니터링 ---------
    private void startLogMonitoring() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    checkLog();

                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void checkLog() {
        String logcatCommand = "logcat -d";
        String line;
        try {
            Process process = Runtime.getRuntime().exec(logcatCommand);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {

//                System.out.println("아아아:"+line);
                // 원하는 로그 메시지 확인
                if (line.contains(data1)) {
                    output_ = data1;
                    break;
                    // 로그가 발생하면 원하는 이벤트 실행
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            handleLogEvent();
////                            System.out.println("output:내부"+output_);
////                            new ReverseTask().execute("http://192.168.0.165:7878");
//                        }
//                    });
                } else if (line.contains(data2)) {
                    output_ = data2;
                    break;
                    // 로그가 발생하면 원하는 이벤트 실행
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            handleLogEvent();
////                            System.out.println("output:내부"+output_);
////                            new ReverseTask().execute("http://192.168.0.165:7878");
//                        }
//                    });
                } else if (line.contains(data3)) {
                    output_ = data3;
                    break;
                    // 로그가 발생하면 원하는 이벤트 실행
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            handleLogEvent();
////                            System.out.println("output:내부"+output_);
////                            new ReverseTask().execute("http://192.168.0.165:7878");
//                        }
//                    });
                } else if (line.contains(data4)) {
                    output_ = data4;
                    break;
                    // 로그가 발생하면 원하는 이벤트 실행
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            handleLogEvent();
////                            System.out.println("output:내부"+output_);
////                            new ReverseTask().execute("http://192.168.0.165:7878");
//                        }
//                    });
                } else if (line.contains(data5)) {
                    output_ = data5;
                    break;
                    // 로그가 발생하면 원하는 이벤트 실행
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            handleLogEvent();
////                            System.out.println("output:내부"+output_);
//
//                        }
//                    });
                }


            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("output:내부끝" + output_);
        new ReverseTask().execute("http://192.168.0.165:7878");
//        output_ = null;
//        return output_;
    }

    private void handleLogEvent() {

        System.out.println("전송할 준비가 되었습니다");
//        static String variable = "일반쓰레기";
    }

// -----------전송 task -------------
    public class ReverseTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... urls) {
        String output_1 = UnityHandlerActivity.output_;
        System.out.println("output_:"+output_);
        HttpURLConnection con = null;
        BufferedReader writer = null;
        try {
            URL url = new URL(urls[0]);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "application/json");

//            System.out.println("????");

            String trashText = null;

            if(output_ != null){


            if ( output_.contains("일반쓰레기")){
                trashText = data1;
            }

            else if(output_.contains("종이쓰레기")){
                trashText = data2;
            }

            else if (output_.contains("플라스틱")){
                trashText = data3;
            }

            else if (output_.contains("고철")){
                trashText = data4;
            }
            else if ( output_.contains("유리")){
                trashText = data5;
            }
            }
            JSONObject jsonParam = new JSONObject(); // jsonobject 로 만들어주기

            jsonParam.put("text",trashText);
//            jsonParam.put("text","종이쓰레기");

//            System.out.println("!!!!"+trashText);

            System.out.println("쓰레기 이름" + jsonParam);

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonParam.toString());
            wr.flush();
            wr.close();

            writer = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();


            while ((inputLine = writer.readLine()) != null) {
            response.append(inputLine);
            }

            writer.close();


//            return jsonParam.toString(); // return 그냥 json 형태 그대로 내보냄.

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    protected void onPostExecute(String result){
        super.onPostExecute(result);


}
}}