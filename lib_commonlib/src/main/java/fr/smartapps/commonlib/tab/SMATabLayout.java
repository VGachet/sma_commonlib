package fr.smartapps.commonlib.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.smartapps.commonlib.R;

/**
 * Created by vincentchann on 23/08/16.
 */
public class SMATabLayout extends TabLayout {

    /*
    Attributes
     */
    private String TAG = "SMATabLayout";
    protected Context context;
    protected List<Drawable> iconList = new ArrayList<>();
    protected List<String> titleList = new ArrayList<>();
    protected int tabCount = 0;
    protected ViewPager viewPager;
    protected ColorStateList stateListColorTitle;
    protected Typeface typefaceTitle;
    protected int firstSelectedTabIndex = 0;
    protected int textSize = 0;

    /*
    Constructors
     */
    public SMATabLayout(Context context) {
        super(context);
        this.context = context;
    }

    public SMATabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SMATabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /*
    Public methods
     */
    public SMATabLayout icons(Drawable... drawables) {
        if (drawables != null & drawables.length > 0) {
            iconList.addAll(Arrays.asList(drawables));
            tabCount = iconList.size();
        }
        return this;
    }

    public SMATabLayout icons(StateListDrawable... drawables) {
        if (drawables != null & drawables.length > 0) {
            iconList.addAll(Arrays.asList(drawables));
            tabCount = iconList.size();
        }
        return this;
    }

    public SMATabLayout icons(List<Drawable> drawableList) {
        this.iconList = drawableList;
        tabCount = iconList.size();
        return this;
    }

    public SMATabLayout titles(String... titles) {
        if (titles != null & titles.length > 0) {
            titleList.addAll(Arrays.asList(titles));
            tabCount = titleList.size();
        }
        return this;
    }

    public SMATabLayout titles(List<String> titleList) {
        this.titleList = titleList;
        tabCount = titleList.size();
        return this;
    }

    public SMATabLayout stateListColorTitle(ColorStateList colorStateList) {
        this.stateListColorTitle = colorStateList;
        return this;
    }

    public SMATabLayout typeFace(Typeface typeface) {
        this.typefaceTitle = typeface;
        return this;
    }

    public SMATabLayout textSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public SMATabLayout viewPager(ViewPager viewPager) {
        if (viewPager != null) {
            this.viewPager = viewPager;
            tabCount = viewPager.getAdapter().getCount();
        }
        return this;
    }

    public void create(SMATabMode tabMode) {
        createTabs();
        setupTabs(tabMode);
    }

    public void selectTab(int index) {
        Tab tab = getTabAt(index);
        tab.select();
    }

    /*
    Protected methods
     */
    protected void createTabs() {
        // create tabs with viewpager automatically
        if (viewPager != null && viewPager.getAdapter() != null) {
            setupWithViewPager(viewPager);
        }

        // create tab
        else {
            for (int count = 0; count < tabCount; count++) {
                addTab(newTab());
            }
        }
    }

    protected void setupTabs(SMATabMode tabMode) {
        for (int count = 0; count < tabCount; count++) {
            if (getTabAt(count) != null) {

                // itemView
                View itemView = null;
                switch (tabMode) {
                    case HORIZONTAL:
                        itemView = LayoutInflater.from(context).inflate(R.layout.tab_icon_title_horizontal, null);
                        break;

                    case VERTICAL:
                        itemView = LayoutInflater.from(context).inflate(R.layout.tab_icon_title_vertical, null);
                        break;
                }

                // title
                TextView title = (TextView) itemView.findViewById(R.id.tab_text);
                if (titleList.size() > count && titleList.get(count) != null) {
                    title.setText(titleList.get(count));
                    if(textSize != 0)
                        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                    if (stateListColorTitle != null) {
                        title.setTextColor(stateListColorTitle);
                    }
                    if (typefaceTitle != null) {
                        title.setTypeface(typefaceTitle);
                    }
                } else {
                    title.setVisibility(GONE);
                }

                // icon
                ImageView icon = (ImageView) itemView.findViewById(R.id.tab_icon);
                if (iconList.size() > count && iconList.get(count) != null) {
                    icon.setImageDrawable(iconList.get(count));
                } else {
                    icon.setVisibility(GONE);
                }

                if (count == firstSelectedTabIndex) {
                    itemView.setSelected(true);
                    getTabAt(count).select();
                }

                // tab
                getTabAt(count).setCustomView(itemView);
            }
        }
    }

}
