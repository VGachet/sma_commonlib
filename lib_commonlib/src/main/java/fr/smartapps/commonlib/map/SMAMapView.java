package fr.smartapps.commonlib.map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qozix.tileview.TileView;

import java.util.ArrayList;
import java.util.List;

import fr.smartapps.commonlib.map.bitmapprovider.BitmapProviderMapView;
import fr.smartapps.commonlib.map.callout.SampleCallout;
import fr.smartapps.commonlib.map.listener.BitmapProviderListener;
import fr.smartapps.commonlib.map.listener.MapCalloutClickListener;

/**
 *
 */
public class SMAMapView extends TileView {

    /*
    Attributes
     */
    protected Context context;
    private String TAG = "MapView";
    protected MapCalloutClickListener mapCalloutClickListener;
    public List<View> tilesViewList = new ArrayList<>();
    protected List<ImageView> pinList = new ArrayList<>();
    public ImageView pinUser;
    public BitmapProviderListener bitmapProviderListener;

    static public String BACKGROUND_MAP_CALLOUT = "#EE888888";
    static public String COLOR_MAP_CALLOUT_TEXT = "#FFFFFF";
    static public String FILE_MAP_CALLOUT_DISCLOSURE = "map_disclosure.png";
    protected int TILE_SIZE = 1024;
    protected int MAP_WIDTH = 0;
    protected int MAP_HEIGHT = 0;
    protected boolean autoSize = false;
    protected float scale = 0;
    protected SampleCallout sampleCallout;
    protected boolean arrow = true;

    private static final double maxLeftPositionCallout = 0.15;
    private static final double maxRightPositionCallout = 0.85;
    private static final float positionXAnchorCalloutLeftCollapsed = -0.13f;
    private static final float positionXAnchorCalloutRightCollapsed = -0.87f;
    private static final float positionXAnchorCalloutNotCollapsed = -0.5f;


    /*
    Constructor
     */
    public SMAMapView(Context context) {
        super(context);
        this.context = context;
    }

    public SMAMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SMAMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    /*
    Callbacks
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        autoSize();
    }

    /*
    Final Methods
     */
    public void init(int width, int height, BitmapProviderListener bitmapProviderListener, MapCalloutClickListener mapCalloutClickListener) {
        this.bitmapProviderListener = bitmapProviderListener;
        this.mapCalloutClickListener = mapCalloutClickListener;
        this.setSize(width, height);
        MAP_WIDTH = width;
        MAP_HEIGHT = height;
    }

    public void movePinUser(double x, double y) {
        double[] bundle = new double[] {x, y};
        pinUser.setTag(bundle);
        moveMarker(pinUser, x, y);
    }

    public void setPinUserRotation(float angle) {
        if (pinUser != null) {
            pinUser.setRotation(angle);
        }
    }

    public void showCallout(final int pinIdx, Context context, double[] position, String message, boolean arrow) {
        slideToAndCenter(position[0], position[1]);

        SampleCallout.MAP_CALLOUT_BACKGROUND = SMAMapView.BACKGROUND_MAP_CALLOUT;
        SampleCallout.MAP_CALLOUT_COLOR_TEXT = SMAMapView.COLOR_MAP_CALLOUT_TEXT;
        SampleCallout.MAP_CALLOUT_FILE_DISCLOSURE = SMAMapView.FILE_MAP_CALLOUT_DISCLOSURE;
        Float anchorXPosition = positionXAnchorCalloutNotCollapsed;
        if (sampleCallout != null) {
            removeCallout(sampleCallout);
        }
        /*if (position[0] <  maxLeftPositionCallout) {
            sampleCallout = new SampleCallout(context, arrow, mapCalloutClickListener, SampleCallout.CALLOUT_COLLAPSED_LEFT);
            anchorXPosition = positionXAnchorCalloutLeftCollapsed;
        } else if (position[0] > maxRightPositionCallout) {
            sampleCallout = new SampleCallout(context, arrow, mapCalloutClickListener, SampleCallout.CALLOUT_COLLAPSED_RIGHT);
            anchorXPosition = positionXAnchorCalloutRightCollapsed;
        } else {*/
            sampleCallout = new SampleCallout(context, arrow, mapCalloutClickListener);
       // }

        sampleCallout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapCalloutClickListener != null)
                    mapCalloutClickListener.onCalloutClick(pinIdx);
            }
        });

        addCallout(sampleCallout, position[0], position[1], anchorXPosition, -1.0f);
        sampleCallout.transitionOut();
        sampleCallout.transitionIn();
        sampleCallout.setTitle(message);


    }

    public void addTileView(Drawable drawable) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        ImageView upSample = new ImageView(context);
        upSample.setImageDrawable(drawable);
        RelativeLayout.LayoutParams logoLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        logoLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(upSample, logoLayoutParams);
        tilesViewList.add(relativeLayout);
        addScalingViewGroup(relativeLayout);
    }

    public void removeTilesView() {
        for(View view : tilesViewList) {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        tilesViewList.clear();
    }

    /*
    Current methods
     */
    public SMAMapView addPin(double x, double y, Drawable pinPathDrawableXHighDensity, String message, int pinIdx) {
        ImageView pinView = new ImageView(context);
        pinView.setImageDrawable(pinPathDrawableXHighDensity);
        pinView.setOnClickListener(markerClickListener);

        double[] point = new double[] {x, y};
        List<Object> bundle = new ArrayList<>();
        bundle.add(0, point);
        bundle.add(1, message);
        bundle.add(2, pinIdx);
        pinView.setId(pinIdx);
        pinView.setTag(bundle);
        pinList.add(pinView);
        addMarker(pinView, x, y, positionXAnchorCalloutNotCollapsed, positionXAnchorCalloutNotCollapsed);
        return this;
    }

    public SMAMapView setPinUser(double x, double y, Drawable pinUserDrawableXHighDensity) {
        pinUser = new ImageView(context);
        Drawable userPinDrawable = pinUserDrawableXHighDensity;
        pinUser.setImageDrawable(userPinDrawable);
        double[] bundle = new double[] {x, y};
        pinUser.setTag(bundle);
        addMarker(pinUser, x, y, positionXAnchorCalloutNotCollapsed, positionXAnchorCalloutNotCollapsed);
        return this;
    }

    public SMAMapView addDetailLevels(String filename, String extension) {
        int zoomInt = 100;
        float zoomFloat = 1.000f;
        int mapSize = TILE_SIZE;

        // set extension .png or .jpg
        for(int i = 0; i < getZoomLevelsFromSize(); i++) {
            // detail levels
            if(i < (getZoomLevelsFromSize() - 1)) {
                addDetailLevel(zoomFloat, filename + "_" + zoomInt + "_%s" + extension, mapSize, mapSize);
            }
            else {
                addDownView(filename + "_" + zoomInt + "_01" + extension);
            }
            zoomFloat /= 2;
            zoomInt /= 2;
        }

        return this;
    }

    public SMAMapView setTilesSize(int tileSize) {
        TILE_SIZE = tileSize;
        return this;
    }

    public SMAMapView autoSize(boolean autoSize, float scale) {
        this.autoSize = autoSize;
        this.scale = scale;
        return this;
    }

    public void setArrow(boolean arrow) {
        this.arrow = arrow;
    }

    public void hidePin(int pinIdx) {
        for(ImageView pin : pinList) {
            if(pin.getId() == pinIdx ) {
                pin.setVisibility(INVISIBLE);
            }
        }
    }

    public void hidePins(List<Integer> pinsIdx) {
        for (ImageView pin : pinList) {
            if(pinsIdx.contains(pin.getId())) {
                pin.setVisibility(INVISIBLE);
            }
        }
    }

    public void showOnePinOnly(int pinIdx) {
        for(ImageView pin : pinList) {
            if(pin.getId() != pinIdx ) {
                pin.setVisibility(INVISIBLE);
            }
        }
    }

    public void showSomePinsOnly(List<Integer> pinsIdx) {
        for (ImageView pin : pinList) {
            if(!pinsIdx.contains(pin.getId())) {
                pin.setVisibility(INVISIBLE);
            }
        }
    }

    public void showAllPins() {
        for(ImageView pin : pinList){
            pin.setVisibility(VISIBLE);
        }
    }

    /*
    Protected methods
     */
    public void init() {
        if (this.bitmapProviderListener != null) {
            BitmapProviderMapView bitmapProvider = new BitmapProviderMapView(this.bitmapProviderListener, MAP_WIDTH, MAP_HEIGHT, TILE_SIZE);
            setBitmapProvider(bitmapProvider);
            setSoundEffectsEnabled(true);
            setShouldRenderWhilePanning(true);
        }
    }

    protected View.OnClickListener markerClickListener = new View.OnClickListener() {
        @Override
        public void onClick( View view ) {
            List<Object> bundle = (List<Object>) view.getTag();
            double[] position = (double[]) bundle.get(0);
            final String message = (String) bundle.get(1);
            final int pinIdx = (int) bundle.get(2);

            slideToAndCenter(position[0], position[1]);
            showCallout(pinIdx, view.getContext(), position, message, arrow);
        }
    };

    protected void autoSize() {
        if (autoSize && MAP_WIDTH > 0 && getWidth() > 0) {
            float mapViewRatio = (float) getHeight() / getWidth();
            float mapDrawableRatio = (float) MAP_HEIGHT / MAP_WIDTH;

            Log.e(TAG, "mapViewRatio : " + mapViewRatio + " = " + getHeight() + " / " + getWidth());
            Log.e(TAG, "mapDrawableRatio : " + mapDrawableRatio + " = " + MAP_HEIGHT + " / " + MAP_WIDTH);

            // image plus haute que la vue
            if (mapDrawableRatio > mapViewRatio) {
                Log.e(TAG, "autoSize FILL");
                setMinimumScaleMode(MinimumScaleMode.FILL);
                setScale(scale);
            }
            // sinon
            else {
                Log.e(TAG, "autoSize FIT");
                setMinimumScaleMode(MinimumScaleMode.FIT);
                setScale(scale);
            }
            autoSize = false;
        }
    }

    protected SMAMapView addDownView(String formattedFilename) {
        ImageView downSample = new ImageView(context);
        downSample.setImageDrawable(bitmapProviderListener.getDrawable(formattedFilename, context));
        addView(downSample, 0);
        return this;
    }

    protected int getZoomLevelsFromSize() {
        int zoomLevelWidth = 1;
        int map_width = MAP_WIDTH;
        while (map_width > TILE_SIZE) {
            map_width = map_width / 2;
            zoomLevelWidth++;
        }

        int zoomLevelHeight = 1;
        int map_height = MAP_HEIGHT;
        while (map_height > TILE_SIZE) {
            map_height = map_height / 2;
            zoomLevelHeight++;
        }

        return Math.max(zoomLevelWidth, zoomLevelHeight);
    }

    public void centerOnPin(double x, double y) {
        slideToAndCenter(x, y);
    }
}
