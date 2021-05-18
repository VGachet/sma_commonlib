package fr.smartapps.commonlib.pager.transformer;

import android.view.View;
import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by owais.ali on 7/31/2016.
 */
public class ZoomOutSideTransformer extends BaseTransformer {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;

    public ZoomOutSideTransformer(SMAViewPager viewPager) {
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {
        view.setPivotX(pageWidth / 2);
        view.setPivotY(pageHeight / 2);
        view.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(position)));
        view.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(position)));
        view.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));
    }
}
