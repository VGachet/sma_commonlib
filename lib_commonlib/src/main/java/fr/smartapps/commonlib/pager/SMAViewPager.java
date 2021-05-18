package fr.smartapps.commonlib.pager;

import android.app.FragmentManager;
import android.content.Context;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Arrays;
import java.util.List;

import fr.smartapps.commonlib.pager.transformer.AccordionTransformer;
import fr.smartapps.commonlib.pager.transformer.BackToFrontTransformer;
import fr.smartapps.commonlib.pager.transformer.CubeDownTransformer;
import fr.smartapps.commonlib.pager.transformer.CubeOutTransformer;
import fr.smartapps.commonlib.pager.transformer.DepthPageTransformer;
import fr.smartapps.commonlib.pager.transformer.FlipHorizontalTransformer;
import fr.smartapps.commonlib.pager.transformer.FlipVerticalTransformer;
import fr.smartapps.commonlib.pager.transformer.FrontToBackTransformer;
import fr.smartapps.commonlib.pager.transformer.NullTransformer;
import fr.smartapps.commonlib.pager.transformer.ParallaxPageTransformer;
import fr.smartapps.commonlib.pager.transformer.RotateDownTransformer;
import fr.smartapps.commonlib.pager.transformer.RotateUpTransformer;
import fr.smartapps.commonlib.pager.transformer.StackTransformer;
import fr.smartapps.commonlib.pager.transformer.TabletTransformer;
import fr.smartapps.commonlib.pager.transformer.ZoomInTransformer;
import fr.smartapps.commonlib.pager.transformer.ZoomOutSideTransformer;
import fr.smartapps.commonlib.pager.transformer.ZoomOutTransformer;

/**
 * Created by vincentchann on 23/08/16.
 */
public class SMAViewPager extends ViewPager {

    /*
    Attributes
     */
    private String TAG = "SMAViewPager";
    protected Context context;
    protected SMAPagerAdapter adapter;
    protected Transition transition = null;

    /*
    Constructor
     */
    public SMAViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public SMAViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /*
    Public methods
     */
    public SMAViewPager fragmentManager(FragmentManager fragmentManager) {
        this.adapter = new SMAPagerAdapter(fragmentManager);
        return this;
    }

    public SMAViewPager setFragments(List<Fragment> fragmentList) {
        if (adapter != null) {
            adapter.setFragmentList(fragmentList);
            setAdapter(adapter);
        }
        else {
            Log.e(TAG, "Adapter of ViewPager is NULL");
        }
        return this;
    }

    public SMAViewPager setFragments(Fragment... fragments) {
        if (adapter != null) {
            adapter.setFragmentList(Arrays.asList(fragments));
            setAdapter(adapter);
        }
        else {
            Log.e(TAG, "Adapter of ViewPager is NULL");
        }
        return this;
    }

    public SMAViewPager preloadAllPages(boolean preload) {
        if (preload) {
            setOffscreenPageLimit(adapter.getCount());
        }
        else {
            setOffscreenPageLimit(1);
        }
        return this;
    }

    public SMAViewPager pageBoundaries(int pageGap) {
        setPadding(pageGap, 0, pageGap, 0);
        setClipToPadding(false);
        setPageMargin(pageGap / 2);
        return this;
    }

    public SMAViewPager pageBoundaries(int pagePadding, int pageMargin) {
        setPadding(pagePadding, 0, pagePadding, 0);
        setClipToPadding(false);
        setPageMargin(pageMargin);
        return this;
    }

    public void create() {
        if (adapter != null) {
        }
    }

    protected boolean swipeable = true;
    public SMAViewPager swipeable(boolean swipeable) {
        this.swipeable = swipeable;
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (swipeable) {
            return super.onTouchEvent(event);
        }
        else {
            return swipeable;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        if (swipeable) {
            return super.onInterceptTouchEvent(event);
        }
        else {
            return swipeable;
        }
    }

    /*
    Functional methods
     */
    public void reloadFragments(List<Fragment> fragmentList) {
        adapter.setFragmentList(fragmentList);
        setAdapter(adapter);
    }

    /*
    Transformer methods
     */
    public SMAViewPager transformer(Transition transition) {
        // for dynamically change transition you must reset adapter
        if (transition != null) {
            final int currentPage = getCurrentItem();
            setAdapter(null);
            setAdapter(adapter);
            setCurrentItem(currentPage);
            adapter.notifyDataSetChanged();
        }

        this.transition = transition;
        switch (transition) {
            case ACCORDION:
                setPageTransformer(true, new AccordionTransformer(this));
                break;
            case BACK_TO_FRONT:
                setPageTransformer(true, new BackToFrontTransformer(this));
                break;
            case CUBE_DOWN:
                setPageTransformer(true, new CubeDownTransformer(this));
                break;
            case CUBE_OUT:
                setPageTransformer(true, new CubeOutTransformer(this));
                break;
            case DEPTH_PAGE:
                setPageTransformer(true, new DepthPageTransformer(this));
                break;
            case FLIP_HORIZONTAL:
                setPageTransformer(true, new FlipHorizontalTransformer(this));
                break;
            case FLIP_VERTICAL:
                setPageTransformer(true, new FlipVerticalTransformer(this));
                break;
            case FRONT_TO_BACK:
                setPageTransformer(true, new FrontToBackTransformer(this));
                break;
            case NULL:
                setPageTransformer(false, new NullTransformer(this));
                break;
            case PARALLAX_PAGE:
                setPageTransformer(true, new ParallaxPageTransformer(this));
                break;
            case ROTATE_DOWN:
                setPageTransformer(true, new RotateDownTransformer(this));
                break;
            case ROTATE_UP:
                setPageTransformer(false, new RotateUpTransformer(this));
                break;
            case STACK:
                setPageTransformer(false, new StackTransformer(this));
                break;
            case TABLET:
                setPageTransformer(false, new TabletTransformer(this));
                break;
            case ZOOM_IN:
                setPageTransformer(false, new ZoomInTransformer(this));
                break;
            case ZOOM_OUT_SIDE:
                setPageTransformer(false, new ZoomOutSideTransformer(this));
                break;
            case ZOOM_OUT:
                setPageTransformer(false, new ZoomOutTransformer(this));
                break;
            default:
                break;
        }
        return this;
    }
}
