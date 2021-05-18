package fr.smartapps.commonlib.pager.transformer;

import android.view.View;
import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by owais.ali on 7/31/2016.
 */
public class CubeOutTransformer extends BaseTransformer {

    public CubeOutTransformer(SMAViewPager viewPager) {
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {
        view.setPivotX(position < 0f ? pageWidth : 0f);
        view.setPivotY(view.getHeight() / 2);
        view.setRotationY(45f * position);
    }
}
