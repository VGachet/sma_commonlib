package fr.smartapps.commonlib.map.bitmapprovider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.qozix.tileview.graphics.BitmapProvider;
import com.qozix.tileview.tiles.Tile;

import fr.smartapps.commonlib.map.listener.BitmapProviderListener;

public class BitmapProviderMapView implements BitmapProvider {

    private String TAG = "BitmapProviderMapView";
    protected BitmapProviderListener bitmapProviderListener;
    protected int MAP_WIDTH;
    protected int MAP_HEIGHT;
    protected int TILE_SIZE;

    public BitmapProviderMapView(BitmapProviderListener bitmapProviderListener, int width, int height, int tileSize) {
        this.bitmapProviderListener = bitmapProviderListener;
        this.MAP_WIDTH = width;
        this.MAP_HEIGHT = height;
        this.TILE_SIZE = tileSize;
    }

    @Override
    public Bitmap getBitmap(Tile tile, Context context) {
        Object data = tile.getData();
        if(bitmapProviderListener != null && data instanceof String) {
            String unformattedFileName = (String) tile.getData();
            String formattedFileName = String.format(unformattedFileName, getTileNumber(tile));
            BitmapDrawable b = (BitmapDrawable)(bitmapProviderListener.getDrawable(formattedFileName, context));
            if(b != null) {
                return b.getBitmap();
            }
        }

        return null;
    }

    public String getTileNumber(Tile tile) {
        int numberOfColumn = getNumberOfColumn(tile.getDetailLevel().getScale());
        int tileX = tile.getColumn() + 1;
        int tileY = tile.getRow() + 1;
        int tileNumber = tileX + (tileY - 1) * numberOfColumn;
        return String.format("%02d", tileNumber);
    }

    public int getNumberOfColumn(float scale) {
        int width = (int) Math.ceil(MAP_WIDTH / getDividedValue(scale));
        return (int) Math.ceil((double) width / (double) TILE_SIZE);
    }

    public int getNumberOfRow(float scale) {
        int height = MAP_HEIGHT / getDividedValue(scale);
        return (int) Math.ceil((double) height / (double) TILE_SIZE);
    }

    public int getDividedValue(float scale) {
        float zoomFloat = 1.000f;
        return Math.round(zoomFloat / scale);
    }
}