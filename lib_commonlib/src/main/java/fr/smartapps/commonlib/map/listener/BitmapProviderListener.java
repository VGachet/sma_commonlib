package fr.smartapps.commonlib.map.listener;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by vincentchann on 20/10/2016.
 */

public interface BitmapProviderListener {

    public Drawable getDrawable(String formattedTileName, Context context);
}
