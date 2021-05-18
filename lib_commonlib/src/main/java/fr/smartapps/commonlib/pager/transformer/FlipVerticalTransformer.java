package fr.smartapps.commonlib.pager.transformer;

import android.view.View;
import fr.smartapps.commonlib.pager.SMAViewPager;

/**
 * Created by owais.ali on 7/31/2016.
 */
public class FlipVerticalTransformer extends BaseTransformer {

    public FlipVerticalTransformer(SMAViewPager viewPager) {
        super(viewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {

        final float rotation = 180f * position;

        view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
        view.setPivotX(pageWidth / 2);
        view.setPivotY(pageHeight / 2);
        view.setTranslationX(pageWidth * -position);
        view.setRotationX(rotation);
    }

    @Override
    protected void onPostTransform(View view, float position, int pageWidth, int pageHeight) {
        super.onPostTransform(view, position, pageWidth, pageHeight);

        if (position > -0.5f && position < 0.5f) {
            view.setVisibility(View.VISIBLE);
        }
        else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
