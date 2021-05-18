package fr.smartapps.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.smartapps.lib.SMAAssetManager;
import fr.smartapps.commonlib.tab.SMATabLayout;
import fr.smartapps.commonlib.tab.SMATabMode;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        SMAAssetManager assetManager = new SMAAssetManager(this);

        // icon list
        List<Drawable> iconList = new ArrayList<>();
        iconList.add(assetManager.getStateListDrawable().selected("icon_tab1.png").inverse("icon_selected_tab1.png"));
        iconList.add(assetManager.getStateListDrawable().selected("icon_tab2.png").inverse("icon_selected_tab2.png"));
        iconList.add(assetManager.getStateListDrawable().selected("icon_tab3.png").inverse("icon_selected_tab3.png"));

        // tab text
        SMATabLayout tabLayout1 = (SMATabLayout) findViewById(R.id.tab1);
        tabLayout1.titles("Tab 1", "Tab 2", "Tab 3").stateListColorTitle(assetManager.getStateListColor().selected("#ffffff").inverse("#cccccc")).create(SMATabMode.VERTICAL);

        // tab icon
        SMATabLayout tabLayout2 = (SMATabLayout) findViewById(R.id.tab2);
        tabLayout2.icons(iconList).create(SMATabMode.VERTICAL);

        // tab text & icon horizontal
        SMATabLayout tabLayout3 = (SMATabLayout) findViewById(R.id.tab3);
        tabLayout3.titles("Tab 1", "Tab 2", "Tab 3").stateListColorTitle(assetManager.getStateListColor().selected("#ffffff").inverse("#cccccc")).icons(iconList).create(SMATabMode.HORIZONTAL);

        // tab text & icon vertical
        SMATabLayout tabLayout4 = (SMATabLayout) findViewById(R.id.tab4);
        tabLayout4.titles("Tab 1", "Tab 2", "Tab 3").stateListColorTitle(assetManager.getStateListColor().selected("#ffffff").inverse("#cccccc")).icons(iconList).create(SMATabMode.VERTICAL);

        // tab text
        SMATabLayout tabLayout5 = (SMATabLayout) findViewById(R.id.tab5);
        tabLayout5.titles("Tab 1", "Tab 2", "Tab 3")
                .stateListColorTitle(assetManager.getStateListColor().selected("#ffffff").inverse("#cccccc"))
                .typeFace(assetManager.getTypeFace("custom.ttf"))
                .create(SMATabMode.VERTICAL);

    }
}
