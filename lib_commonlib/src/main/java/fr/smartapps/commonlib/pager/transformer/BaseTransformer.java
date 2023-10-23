package fr.smartapps.commonlib.pager.transformer;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.View;

import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by owais.ali on 7/31/2016.
 */
public abstract class BaseTransformer implements ViewPager.PageTransformer {

    private final SMAViewPager viewPager;

    public BaseTransformer(SMAViewPager crystalViewPager){
        this.viewPager = crystalViewPager;
    }

    @Override
    public void transformPage(View view, float position) {
        if (position < -1) {
            /*view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);*/

        }
        else if (position <= 1) {
            onPreTransform(view, position, view.getWidth(), view.getHeight());
            onTransform(view, position, view.getWidth(), view.getHeight());
            onPostTransform(view, position, view.getWidth(), view.getHeight());
        } else {
            /*view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);*/
        }
    }

    public void draw(final Canvas canvas, final Paint paint){

    }

    protected SMAViewPager getCrystalViewPager(){
        return this.viewPager;
    }

    protected void onPreTransform(View view, float position, int pageWidth, int pageHeight){}

    protected void onPostTransform(View view, float position, int pageWidth, int pageHeight){}

    protected final void log(Object obj){
        Log.d("CRS=>", String.valueOf(obj));
    }

    protected abstract void onTransform(View view, float position, int pageWidth, int pageHeight);
}
