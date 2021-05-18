package fr.smartapps.commonlib.pager.transformer;

import android.view.View;
import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by owais.ali on 7/31/2016.
 */
public class ZoomOutTransformer extends BaseTransformer {

    public ZoomOutTransformer(SMAViewPager viewPager) {
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {
        view.setPivotX(pageWidth / 2);
        view.setPivotY(pageHeight / 2);
        view.setScaleX(2 - (1 - Math.abs(position)));
        view.setScaleY(2 - (1 - Math.abs(position)));
        view.setAlpha(1 - Math.abs(position));
        view.setTranslationX(pageWidth * -position);
    }
}
