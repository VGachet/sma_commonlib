package fr.smartapps.demo.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import fr.smartapps.demo.R;
import fr.smartapps.lib.SMAAssetManager;
import fr.smartapps.commonlib.video.SMAVideoView;

public class SimpleInitActivity extends AppCompatActivity {

    protected SMAVideoView smaVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_init);

        smaVideoView = (SMAVideoView) findViewById(R.id.smavideoview);
        SMAAssetManager assetManager = new SMAAssetManager(this);
        smaVideoView.create(assetManager.getAssetFileDescriptor("video.mp4"), 840, 480);

    }
}
