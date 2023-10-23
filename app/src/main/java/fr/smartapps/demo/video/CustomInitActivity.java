package fr.smartapps.demo.video;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;

import fr.smartapps.demo.R;
import fr.smartapps.commonlib.video.SMAVideoView;

public class CustomInitActivity extends AppCompatActivity {

    SMAVideoView smaVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_init);

        smaVideoView = (SMAVideoView) findViewById(R.id.smavideoview);
        smaVideoView.thumbDrawable(Utils.getDensityDrawable("thumb.png", DisplayMetrics.DENSITY_XHIGH, this))
                .playDrawable(Utils.getDensityDrawable("play.png", DisplayMetrics.DENSITY_XHIGH, this))
                .pauseDrawable(Utils.getDensityDrawable("pause.png", DisplayMetrics.DENSITY_XHIGH, this))
                .setVideoBackgroundColor("#3F51B5")
                .setPlayerBackgroundColor("#000000")
                .setThumbColor("#ff0000")
                .setProgressFinishedColor("#FF0000")
                .setProgressUnfinishedColor("#ff8888")
                .setTimeColor("#ff0000")
                .setPlayPauseBtnColor("#ff0000")
                .create("video.mp4");
    }
}
