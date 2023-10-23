package fr.smartapps.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import fr.smartapps.lib.SMAAssetManager;
import fr.smartapps.commonlib.imageview.SMAZoomableImageView;

public class ZoomableImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomable_image);
        SMAAssetManager assetManager = new SMAAssetManager(this);

        SMAZoomableImageView zoomableImageView = (SMAZoomableImageView) findViewById(R.id.zoomable_image_view);
        zoomableImageView.setImageDrawable(assetManager.getDrawable("image.jpg"));
        zoomableImageView.setMaxZoom(10);
    }
}
