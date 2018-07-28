package com.unithon.com.shortube.video;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.PlayerUIController;
import com.unithon.com.shortube.R;
import com.unithon.com.shortube.video.comment.VerticalSpaceItemDecoration;
import com.unithon.com.shortube.video.comment.VideoCommentListAdapter;
import com.unithon.com.shortube.video.comment.VideoCommentTestDataBuilder;
import com.unithon.com.shortube.video.highlight.HighlighListAdapter;
import com.unithon.com.shortube.video.highlight.HighlightTestDataBuilder;
import com.unithon.com.shortube.video.highlight.HorizontalSpaceItemDecoration;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

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

//        videoCommentListView.addItemDecoration(new VerticalSpaceItemDecoration(15));

    }

    private void initHighlightList(){

        HighlighListAdapter adapter = new HighlighListAdapter(this);
        adapter.setHighlightDataList(HighlightTestDataBuilder.createTestDataList());
        videoHighlightListView.setAdapter(adapter);
        videoHighlightListView.addItemDecoration(new HorizontalSpaceItemDecoration(15));
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
                        // TODO: 2018. 7. 29. 정지 로직 추가
                        String videoId = getIntent().getStringExtra(PLAY_INTENT);
                        initializedYouTubePlayer.loadVideo(videoId, 0);
                        initializedYouTubePlayer.pause();
                    }

                    // TODO: 2018. 7. 28. 서버로 api 전송하기
                    @Override
                    public void onCurrentSecond(float second) {
                        super.onCurrentSecond(second);
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
                    showSystemUI();
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
        playerUIController.setCustomAction1(getResources().getDrawable(R.drawable.left_arrow), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018. 7. 29. 다음 비디오로 이동
                Toast.makeText(VideoPlayerActivity.this, "left!", Toast.LENGTH_SHORT).show();
            }
        });
        playerUIController.showCustomAction1(true);

        playerUIController.setCustomAction2(getResources().getDrawable(R.drawable.right_arrow), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VideoPlayerActivity.this, "right", Toast.LENGTH_SHORT).show();
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
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }
}
