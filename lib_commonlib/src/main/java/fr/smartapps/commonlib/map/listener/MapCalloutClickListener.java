package fr.smartapps.commonlib.map.listener;

import android.graphics.drawable.Drawable;

/**
 *
 */
public interface MapCalloutClickListener {

    public void onCalloutClick(int pinIdx);

    public Drawable getCalloutDisclosureDrawable();
}
