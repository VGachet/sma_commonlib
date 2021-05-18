package fr.smartapps.demo.video;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rdupont on 10/11/2016.
 */

public class Utils {

    public static Drawable getDensityDrawable(String imageName, int density, Context context) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDensity = density;
        return Drawable.createFromResourceStream(context.getResources(), null, getInputStream(imageName, context), null, opts);
    }

    public static InputStream getInputStream(String fileName, Context context) {
        if(fileName == null || fileName.equals(""))
            return null;
        try {
            return context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AssetFileDescriptor getAssetFileDescriptor(String fileanme, Context context) {

        AssetFileDescriptor assetFileDescriptor;
        try {
            return assetFileDescriptor = context.getAssets().openFd(fileanme);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
