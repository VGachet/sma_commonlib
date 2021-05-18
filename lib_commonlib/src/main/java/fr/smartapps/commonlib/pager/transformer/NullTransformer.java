package fr.smartapps.commonlib.pager.transformer;

import android.view.View;

import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by cdurif on 03/02/2017.
 */

public class NullTransformer extends BaseTransformer {

    public NullTransformer(SMAViewPager viewPager){
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {

        view.setTranslationX(view.getWidth() * -position);

        if (position <= -1.0F || position >= 1.0F) {
            view.setAlpha(0.0F);
            view.setVisibility(View.INVISIBLE);
        }
        else if (position == 0.0F) {
            view.setAlpha(1.0F);
            view.setVisibility(View.VISIBLE);

        }
    }
}
