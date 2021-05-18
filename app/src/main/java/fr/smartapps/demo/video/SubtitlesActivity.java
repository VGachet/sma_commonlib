package fr.smartapps.demo.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.smartapps.commonlib.video.SMAVideoView;
import fr.smartapps.demo.R;

public class SubtitlesActivity extends AppCompatActivity {

    private SMAVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtitles);

        mVideoView = (SMAVideoView) findViewById(R.id.subtitles_video_view);

        mVideoView
                .autoPlay(true)
                .setLocale("en")
                .subtitlesForced(false)
                .create(Utils.getAssetFileDescriptor("subtitles.mp4", this));

    }
}
