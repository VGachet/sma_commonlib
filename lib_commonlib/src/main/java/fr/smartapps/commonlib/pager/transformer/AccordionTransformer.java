package fr.smartapps.commonlib.pager.transformer;

import android.view.View;

import fr.smartapps.commonlib.pager.SMAViewPager;


/**
 * Created by owais.ali on 7/31/2016.
 */
public class AccordionTransformer extends BaseTransformer {

    public AccordionTransformer(SMAViewPager viewPager) {
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {
        view.setTranslationX(pageWidth / 2 * -position);
        view.setScaleX(1 - Math.abs(position));
    }
}
