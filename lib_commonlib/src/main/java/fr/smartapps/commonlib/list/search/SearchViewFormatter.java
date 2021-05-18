package fr.smartapps.commonlib.list.search;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchViewFormatter {

    protected Drawable mSearchBackGround;
    protected Drawable mSearchIcon;
    protected boolean mSearchIconInside = false;
    protected boolean mSearchIconOutside = false;
    protected Drawable mSearchVoiceIcon = null;
    protected ColorStateList mSearchTextColor = null;
    protected ColorStateList mSearchHintColor = null;
    protected String mSearchHintText = "";
    protected int mInputType = Integer.MIN_VALUE;
    protected Drawable mSearchCloseIcon = null;
    protected TextView.OnEditorActionListener mEditorActionListener;
    protected Resources mResources;

    public SearchViewFormatter setSearchBackGroundDrawable(Drawable mSearchBackGround) {
        this.mSearchBackGround = mSearchBackGround;
        return this;
    }

    public SearchViewFormatter setSearchIconDrawable(Drawable searchIconDrawable, boolean inside, boolean outside) {
        mSearchIcon = searchIconDrawable;
        mSearchIconInside = inside;
        mSearchIconOutside = outside;
        return this;
    }

    public SearchViewFormatter setSearchVoiceIconDrawable(Drawable searchVoiceIcon) {
        this.mSearchVoiceIcon = searchVoiceIcon;
        return this;
    }

    public SearchViewFormatter setSearchTextColorStateList(ColorStateList searchTextColorResource) {
        mSearchTextColor = searchTextColorResource;
        return this;
    }

    public SearchViewFormatter setSearchHintColorStateList(ColorStateList searchHintColor) {
        mSearchHintColor = searchHintColor;
        return this;
    }

    public SearchViewFormatter setSearchHintText(String searchHintText) {
        mSearchHintText = searchHintText;
        return this;
    }

    public SearchViewFormatter setInputType(int inputType) {
        mInputType = inputType;
        return this;
    }

    public SearchViewFormatter setSearchCloseIconDrawable(Drawable searchCloseIcon) {
        mSearchCloseIcon = searchCloseIcon;
        return this;
    }

    public SearchViewFormatter setEditorActionListener(TextView.OnEditorActionListener editorActionListener) {
        mEditorActionListener = editorActionListener;
        return this;
    }

    public void format(SearchView searchView) {
        if (searchView == null) {
            return;
        }

        mResources = searchView.getContext().getResources();
        int id;

        if (mSearchBackGround != null) {
            id = getIdentifier("search_plate");
            View view = searchView.findViewById(id);
            view.setBackground(mSearchBackGround);

            id = getIdentifier("submit_area");
            view = searchView.findViewById(id);
            view.setBackground(mSearchBackGround);
        }

        if (mSearchVoiceIcon != null) {
            id = getIdentifier("search_voice_btn");
            ImageView view = (ImageView) searchView.findViewById(id);
            view.setImageDrawable(mSearchVoiceIcon);
        }

        if (mSearchCloseIcon != null) {
            id = getIdentifier("search_close_btn");
            ImageView view = (ImageView) searchView.findViewById(id);
            view.setImageDrawable(mSearchCloseIcon);
        }

        id = getIdentifier("search_src_text");
        TextView view = (TextView) searchView.findViewById(id);
        if (mSearchTextColor != null) {
            view.setTextColor(mSearchTextColor);
        }
        if (mSearchHintColor != null) {
            view.setHintTextColor(mSearchHintColor);
        }
        if (mInputType > Integer.MIN_VALUE) {
            view.setInputType(mInputType);
        }
        if (mSearchIcon != null) {
            ImageView imageView = (ImageView) searchView.findViewById(getIdentifier("search_mag_icon"));

            if (mSearchIconInside) {
                Drawable searchIconDrawable = mSearchIcon;
                int size = (int) (view.getTextSize() * 1.25f);
                searchIconDrawable.setBounds(0, 0, size, size);

                SpannableStringBuilder hintBuilder = new SpannableStringBuilder("   ");
                hintBuilder.append(mSearchHintText);
                hintBuilder.setSpan(
                        new ImageSpan(searchIconDrawable),
                        1,
                        2,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );

                view.setHint(hintBuilder);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            }
            if (mSearchIconOutside) {
                int searchImgId = getIdentifier("search_button");
                imageView = (ImageView) searchView.findViewById(searchImgId);

                imageView.setImageDrawable(mSearchIcon);
            }
        }

        if (mEditorActionListener != null) {
            view.setOnEditorActionListener(mEditorActionListener);
        }
    }

    protected int getIdentifier(String literalId) {
        return mResources.getIdentifier(
                String.format("android:id/%s", literalId),
                null,
                null
        );
    }
}