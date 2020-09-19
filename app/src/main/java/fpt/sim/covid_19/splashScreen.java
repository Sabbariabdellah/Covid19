package fpt.sim.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import java.net.URI;

public class splashScreen extends AppCompatActivity {

    VideoView videoView;
    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        videoView = findViewById(R.id.videoView);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo2);
        videoView.setVideoURI(video);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });
        getSupportActionBar().hide();
        videoView.start();
    }
    public void startNextActivity(){
        if(isFinishing())
            return;
        sharedPreferences = getSharedPreferences("firstUse",MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("first", true);
        if (isFirst){
            startActivity(new Intent(splashScreen.this, welcomeScreen.class));
            finish();
        }else{
            startActivity(new Intent(splashScreen.this, MainActivity.class));
            finish();
        }

    }
}