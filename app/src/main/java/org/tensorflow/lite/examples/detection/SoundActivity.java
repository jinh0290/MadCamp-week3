package org.tensorflow.lite.examples.detection;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.zip.Inflater;

public class SoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soundlayout);

        new Thread(){
            @Override
            public void run() {
                tts("하운님 마스크를 써주세요!!");
            }
        }.start();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SoundActivity.this, DetectorActivity.class);
                startActivity(intent);//액티비티 띄우기
                finish();
            }
        }, 2000);
        //Toast.makeText(getApplicationContext(),"eeeeeeeee", Toast.LENGTH_SHORT).show();

    }

    public void tts(String text){
        String clientId = "njf0wwsu8v";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "eJcoZcQ2kswjaAfIVKn39I0el3DtO4nJZ6n6mHeG";// 애플리케이션 클라이언트 시크릿값";\

        try {

            String te = URLEncoder.encode(text, "utf-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/voice-premium/v1/tts";

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker=nara&volume=0&speed=0&pitch=0&emotion=0&format=mp3&text=" + te;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 mp3 파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(getFilesDir(),tempname + ".mp3");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();

                String Path_to_file = f.getPath();
                //System.out.println(Path_to_file);
                //System.out.println(f.get);
                MediaPlayer audioPlay = new MediaPlayer();
                audioPlay.setDataSource(Path_to_file);
                audioPlay.prepare();//이걸 해줘야 하는군. 없으면 에러난다.
                audioPlay.start();
                //Toast.makeText(getApplicationContext(), "pleasesdsdsd", Toast.LENGTH_SHORT).show();




            } else {  // 오류 발생
                //Toast.makeText(getApplicationContext(), "eeeeeeee", Toast.LENGTH_SHORT).show();
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "fffffffff", Toast.LENGTH_SHORT).show();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(e);
        }

    }
}

