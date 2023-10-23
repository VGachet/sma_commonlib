package fr.smartapps.demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import fr.smartapps.lib.SMAAssetManager;
import fr.smartapps.commonlib.map.SMAMapView;
import fr.smartapps.commonlib.map.listener.BitmapProviderListener;
import fr.smartapps.commonlib.map.listener.MapCalloutClickListener;

public class MapviewActivity extends AppCompatActivity {

    private String TAG = "MapviewActivity";
    protected SMAAssetManager assetManager;
    protected SMAMapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview_activity);
        assetManager = new SMAAssetManager(this);
        initMap();
        addPinUser();
        addPOIPins();
    }

    protected void initMap() {
        mapView = (SMAMapView) findViewById(R.id.map);
        // media59321 : 2300, 4083
        // media50947 : 6000, 3750
        mapView.init(2300, 4083,
                new BitmapProviderListener() {
                    @Override
                    public Drawable getDrawable(String formattedTileName, Context context) {
                        Log.e(TAG, "tile loaded : " + formattedTileName);
                        return assetManager.getDrawable(formattedTileName);
                    }
                },
                new MapCalloutClickListener() {
                    @Override
                    public void onCalloutClick(int pinIdx) {
                        Toast.makeText(getApplicationContext(), "POI : " + pinIdx, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public Drawable getCalloutDisclosureDrawable() {
                        return assetManager.getDrawable("map_disclosure.png").filter("#f25524");
                    }
                });

        mapView.addDetailLevels("media59321", ".jpg");
        mapView.setScaleLimits(0, 3);
        mapView.autoSize(true, 0);
        mapView.defineBounds(0, 0, 1, 1);
        mapView.setArrow(false);
        mapView.BACKGROUND_MAP_CALLOUT = "#ffffff";
        mapView.COLOR_MAP_CALLOUT_TEXT = "#f25524";
    }

    protected int moveCounter = 0;
    protected void addPinUser() {
        mapView.setPinUser(0.5, 0.5, assetManager.getDrawable("pin_user.png").density(5));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {

                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                float positionX = 0.5f;
                                float positionY = 0.5f;
                                switch (moveCounter % 6) {
                                    case 0:
                                        mapView.setPinUserRotation(20);
                                        positionX = 0.25f;
                                        positionY = 0.25f;
                                        break;
                                    case 1:
                                        mapView.setPinUserRotation(40);
                                        positionX = 0.25f;
                                        positionY = 0.75f;
                                        break;
                                    case 2:
                                        mapView.setPinUserRotation(60);
                                        positionX = 0.75f;
                                        positionY = 0.75f;
                                        break;
                                    case 3:
                                        mapView.setPinUserRotation(80);
                                        positionX = 0.50f;
                                        positionY = 0.75f;
                                        break;
                                    case 4:
                                        mapView.setPinUserRotation(160);
                                        positionX = 0.50f;
                                        positionY = 0.50f;
                                        break;
                                    case 5:
                                        mapView.setPinUserRotation(190);
                                        positionX = 0.75f;
                                        positionY = 0.25f;
                                        break;
                                }
                                mapView.movePinUser(positionX, positionY);
                                moveCounter++;
                            }
                        });
                    } catch (InterruptedException e) {
                        // ooops
                    }
                }
            }
        }).start();
    }

    protected void addPOIPins() {
        mapView.addPin(0.20f, 0.50f, assetManager.getDrawable("map_pin.png").density(5), "pin 1", 1);
        mapView.addPin(0.25f, 0.45f, assetManager.getDrawable("map_pin.png").density(5), "pin 2", 2);
        mapView.addPin(0.35f, 0.75f, assetManager.getDrawable("map_pin.png").density(5), "pin 3", 3);
        mapView.addPin(0.75f, 0.20f, assetManager.getDrawable("map_pin.png").density(5), "pin 4", 4);
        mapView.addPin(0.65f, 0.50f, assetManager.getDrawable("map_pin.png").density(5), "pin 5", 5);
    }
}
