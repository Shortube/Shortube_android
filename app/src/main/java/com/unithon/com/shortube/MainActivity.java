package com.unithon.com.shortube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unithon.com.shortube.video.VideoPlayerActivity;

import static com.unithon.com.shortube.Common.PLAY_INTENT;

public class MainActivity extends AppCompatActivity {
    String PLAY_URL_KEY = "TLRi6oT_hAI";
    Button btToPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btToPlayer = findViewById(R.id.bt_to_video_player);

        btToPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VideoPlayerActivity.class);
                intent.putExtra(PLAY_INTENT, PLAY_URL_KEY);
                startActivity(intent);
            }
        });
    }
}
