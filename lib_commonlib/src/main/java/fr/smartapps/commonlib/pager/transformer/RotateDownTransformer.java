package fr.smartapps.commonlib.pager.transformer;

import android.view.View;
import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by owais.ali on 7/31/2016.
 */
public class RotateDownTransformer extends BaseTransformer {

    private static final int ROTATION = 25;

    public RotateDownTransformer(SMAViewPager viewPager) {
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {
        view.setPivotX(pageWidth / 2);
        view.setPivotY(pageHeight);
        view.setRotation(ROTATION * position);
    }
}
