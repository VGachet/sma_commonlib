package fr.smartapps.commonlib.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

/**
 * Created by vincentchann on 07/08/16.
 */
public class SMAAdapter extends RecyclerView.Adapter<SMAViewHolder> {

    protected SMAListListener SMAListListener;
    protected List<SMADataView> dataViews;
    protected int lastAnimatePosition = 0;
    protected int enterAnimation;
    protected int animationStepDelay;
    protected Context context;

    /*
    Constructor
    */
    public SMAAdapter(Context context, List<SMADataView> dataViews, SMAListListener SMAListListener) {
        this.dataViews = dataViews;
        this.SMAListListener = SMAListListener;
        this.context = context;
    }

    /*
    Callbacks
     */
    @Override
    public int getItemCount() {
        if (dataViews != null) {
            return dataViews.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public SMAViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (dataViews != null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new SMAViewHolder(inflater.inflate(dataViews.get(position).resourceViewIdx, parent, false));
        }
        else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(SMAViewHolder holder, int position) {
        if (SMAListListener != null && dataViews != null) {
            SMAListListener.onBindViewHolder(holder.itemView, dataViews.get(position));

            // full span layout
            if (dataViews.get(position).getFullWidth()) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(true);
                holder.itemView.setLayoutParams(layoutParams);
            }
            else {
                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(false);
                holder.itemView.setLayoutParams(layoutParams);
            }

            // animate
            setAnimation(holder.itemView, position);
        }
    }

    public void reloadData(List<SMADataView> dataViews) {
        this.dataViews = dataViews;
        notifyDataSetChanged();
    }

    public void insertData(SMADataView dataView, int positionInserted) {
        this.dataViews.add(positionInserted, dataView);
        notifyItemInserted(positionInserted);
    }

    public void removeData(int positionRemoved) {
        this.dataViews.remove(positionRemoved);
        notifyItemRemoved(positionRemoved);
    }

    public void reloadDataWithAnimation(List<SMADataView> dataViews, int lastAnimatePosition, int enterAnimation) {
        this.dataViews = dataViews;
        this.lastAnimatePosition = lastAnimatePosition;
        this.enterAnimation = enterAnimation;
        notifyDataSetChanged();
    }

    protected void setAnimation(View view, int position) {
        if (position < lastAnimatePosition) {
            Animation animation = AnimationUtils.loadAnimation(context, enterAnimation);
            animation.setStartOffset(position * animationStepDelay);
            view.startAnimation(animation);
        } else {
            lastAnimatePosition = 0;
        }

    }
}
