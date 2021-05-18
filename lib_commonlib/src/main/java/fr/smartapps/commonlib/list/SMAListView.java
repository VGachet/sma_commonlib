package fr.smartapps.commonlib.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by vincentchann on 07/08/16.
 */
public class SMAListView extends RecyclerView {

    /*
    Attributes
     */
    protected Context context;
    protected int columnNumber = 1;
    protected StaggeredGridLayoutManager staggeredGridLayoutManager;
    protected SMAAdapter adapter;
    protected int DEFAULT_ANIMATION_STEP_DELAY = 70;

    /*
    Default constructors
     */
    public SMAListView(Context context) {
        super(context);
        this.context = context;
    }

    public SMAListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SMAListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    /*
    Callback
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (adapter != null) {
            setAdapter(adapter);
            setHasFixedSize(true);
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(columnNumber, VERTICAL);
            setLayoutManager(staggeredGridLayoutManager);

            /*RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            this.setItemAnimator(itemAnimator);*/
        }
    }

    public void initData(int columnNumber, List<SMADataView> dataViews, SMAListListener SMAListListener) {
        this.adapter = new SMAAdapter(context, dataViews, SMAListListener);
        this.columnNumber = columnNumber;
    }

    /*
    Reload data
     */
    public void reloadData(List<SMADataView> dataViews) {
        if (adapter != null) {
            adapter.reloadData(dataViews);
        }
    }

    public void insertData(SMADataView dataView, int positionInserted) {
        if (adapter != null) {
            adapter.insertData(dataView, positionInserted);
        }
    }

    public void removeData(int positionRemoved) {
        if (adapter != null) {
            adapter.removeData(positionRemoved);
        }
    }

    /*
    Reload data with animation (only on visible views)
     */
    public void reloadDataWithStartAnimation(List<SMADataView> dataViews, int animation, int animationStepDelay) {
        if (adapter != null) {
            adapter.animationStepDelay = animationStepDelay;
            adapter.reloadDataWithAnimation(dataViews, staggeredGridLayoutManager.findLastVisibleItemPositions(null)[0] + 1, animation);
        }
    }

    public void reloadDataWithStartAnimation(List<SMADataView> dataViews, int animation) {
        this.reloadDataWithStartAnimation(dataViews, animation, DEFAULT_ANIMATION_STEP_DELAY);
    }

    /*
    Reload data with animation (on all views)
    TODO not working well for the moment
     */
    /*protected void reloadAllDataWithAnimation(List<SMADataView> dataViews, int animation, int animationStepDelay) {
        if (adapter != null) {
            adapter.animationStepDelay = animationStepDelay;
            adapter.reloadDataWithAnimation(dataViews, adapter.dataViews.size() - 1, animation);
        }
    }

    protected void reloadAllDataWithAnimation(List<SMADataView> dataViews, int animation) {
        reloadAllDataWithAnimation(dataViews, animation, DEFAULT_ANIMATION_STEP_DELAY);
    }*/
}
