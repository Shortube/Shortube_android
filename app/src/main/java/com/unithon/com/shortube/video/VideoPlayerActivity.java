package com.unithon.com.shortube.video;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.PlayerUIController;
import com.unithon.com.shortube.R;
import com.unithon.com.shortube.video.comment.VideoCommentListAdapter;
import com.unithon.com.shortube.video.comment.VideoCommentTestDataBuilder;
import com.unithon.com.shortube.video.highlight.HighlightListAdapter;
import com.unithon.com.shortube.video.highlight.HighlightTestDataBuilder;
import com.unithon.com.shortube.video.highlight.HorizontalSpaceItemDecoration;
import com.unithon.com.shortube.video.inter.VideoHighlightTrigger;
import com.unithon.com.shortube.video.inter.VideoSectionJumper;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.unithon.com.shortube.Common.PLAY_INTENT;


/**
 * Created by macbook on 2018. 7. 28..
 */

public class VideoPlayerActivity extends AppCompatActivity {
    private static final String TAG = VideoPlayerActivity.class.getSimpleName();

    private RecyclerView videoHighlightListView;
    private RecyclerView videoCommentListView;
    private YouTubePlayerView youtubePlayerView;
    private CustomVideoTracker youTubePlayerTracker;
    private YouTubePlayer youTubePlayer;

    private HighlightPlayStateObserver highlightPlayStateObserver;

    private TextView tvVideoTitle;
    private TextView authorNickName;
    private CircleImageView authorThumb;

    private boolean isHighlightMode = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        tvVideoTitle = findViewById(R.id.tv_video_title);
        authorNickName = findViewById(R.id.tv_video_owner_nickname);
        authorThumb = findViewById(R.id.video_comment_profile_thumb);

        // TODO: 2018. 7. 29. 썸네일 오류 수정
//        Glide.with(getApplicationContext()).load("https://img.youtube.com/vi/k_4p7xyE_ok/0.jpg").into(authorThumb);

        youtubePlayerView = findViewById(R.id.youtube_player_view);

        videoCommentListView = findViewById(R.id.video_comment_list_view);
        videoCommentListView.setLayoutManager(new LinearLayoutManager(this));
        videoHighlightListView = findViewById(R.id.video_highlight_list_view);
        videoHighlightListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        initVideoPlayer();
        initCommentList();
        initHighlightList();

        getLifecycle().addObserver(youtubePlayerView);
    }


    private void initCommentList() {
        VideoCommentListAdapter adapter = new VideoCommentListAdapter(this);
        adapter.setVideoSectionJumper(new VideoSectionJumper() {
            @Override
            public void jump(long destination) {
                youTubePlayer.seekTo(destination);
            }
        });
        adapter.setData(VideoCommentTestDataBuilder.createTestDataList());
        videoCommentListView.setAdapter(adapter);
    }

    private void initHighlightList(){
        HighlightListAdapter adapter = new HighlightListAdapter(this);
        adapter.setHighlightDataList(HighlightTestDataBuilder.createTestDataList());
        adapter.setVideoHighlightTrigger(new VideoHighlightTrigger() {
            @Override
            public void trigger(long start, long end) {
                isHighlightMode = true;
                highlightPlayStateObserver = new HighlightPlayStateObserver(start, end);
                youTubePlayer.seekTo(start);
                youTubePlayer.play();
            }
        });

        videoHighlightListView.addItemDecoration(new HorizontalSpaceItemDecoration(15));
        videoHighlightListView.setAdapter(adapter);
    }

    private void initVideoPlayer() {
        initPlayerUIController();
        initPlayerTracker();

        youtubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                youTubePlayer = initializedYouTubePlayer;
                youTubePlayer.addListener(youTubePlayerTracker);
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = getIntent().getStringExtra(PLAY_INTENT);
                        initializedYouTubePlayer.loadVideo(videoId, 0);
                    }

                    // TODO: 2018. 7. 28. 서버로 api 전송하기
                    @Override
                    public void onCurrentSecond(float second) {
                        super.onCurrentSecond(second);
                        if(isHighlightMode){
                            if(highlightPlayStateObserver.isTimeToFinish((long)second)){
                                initializedYouTubePlayer.pause();
                                isHighlightMode = false;

                                youtubePlayerView.getPlayerUIController().showCustomAction1(true);
                                youtubePlayerView.getPlayerUIController().showCustomAction2(true);
                                youtubePlayerView.getPlayerUIController().showUI(true);
                                youtubePlayerView.getPlayerUIController().getMenu();
                            }
                        }
                    }
                });
            }
        }, true);
    }

    private void initPlayerUIController() {
        PlayerUIController playerUIController = youtubePlayerView.getPlayerUIController();
        playerUIController.setFullScreenButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (youtubePlayerView.isFullScreen()) {
//                    showSystemUI();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    youtubePlayerView.exitFullScreen();
                } else {
                    hideSystemUI();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    youtubePlayerView.enterFullScreen();
                }

            }
        });

        playerUIController.showYouTubeButton(false);
        playerUIController.setCustomAction1(getResources().getDrawable(R.drawable.prev), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018. 7. 29. 다음 비디오로 이동
                Toast.makeText(VideoPlayerActivity.this, "이전 하이라이트를 재생합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        playerUIController.showCustomAction1(true);

        playerUIController.setCustomAction2(getResources().getDrawable(R.drawable.next), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VideoPlayerActivity.this, "다음 하이라이트를 재생합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        playerUIController.showCustomAction2(true);
    }

    private void initPlayerTracker() {
        youTubePlayerTracker = new CustomVideoTracker(new CustomVideoTracker.StateTrackListener() {
            @Override
            public void onPause() {
                youtubePlayerView.getPlayerUIController().showCustomAction1(true);
                youtubePlayerView.getPlayerUIController().showCustomAction2(true);
            }

            @Override
            public void onEnded() {
                youtubePlayerView.getPlayerUIController().showCustomAction1(true);
                youtubePlayerView.getPlayerUIController().showCustomAction2(true);
            }
        });
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
