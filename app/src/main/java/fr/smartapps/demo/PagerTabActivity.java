package fr.smartapps.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.smartapps.lib.SMAAssetManager;
import fr.smartapps.commonlib.pager.SMAViewPager;
import fr.smartapps.commonlib.tab.SMATabLayout;
import fr.smartapps.commonlib.tab.SMATabMode;

public class PagerTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab);

        // pager
        SMAAssetManager assetManager = new SMAAssetManager(this);
        SMAViewPager pager = (SMAViewPager) findViewById(R.id.pager);
        pager.fragmentManager(getFragmentManager()).setFragments(new Fragment1(), new Fragment2(), new Fragment3()).create();
        
        // tab
        List<Drawable> iconList = new ArrayList<>();
        iconList.add(assetManager.getStateListDrawable().selected("icon_tab1.png").inverse("icon_selected_tab1.png"));
        iconList.add(assetManager.getStateListDrawable().selected("icon_tab2.png").inverse("icon_selected_tab2.png"));
        iconList.add(assetManager.getStateListDrawable().selected("icon_tab3.png").inverse("icon_selected_tab3.png"));

        SMATabLayout tabLayout = (SMATabLayout) findViewById(R.id.tab);
        tabLayout.titles("Tab 1", "Tab 2", "Tab 3")
                .viewPager(pager)
                .icons(iconList)
                .stateListColorTitle(assetManager.getStateListColor().selected("#ffffff").inverse("#cccccc"))
                .create(SMATabMode.VERTICAL);
    }
}
