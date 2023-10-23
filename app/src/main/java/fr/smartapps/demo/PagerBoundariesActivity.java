package fr.smartapps.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import fr.smartapps.commonlib.pager.SMAViewPager;

public class PagerBoundariesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_boundaries);

        // pager
        SMAViewPager pager1 = (SMAViewPager) findViewById(R.id.pager1);
        pager1.fragmentManager(getFragmentManager())
                .setFragments(new Fragment1(), new Fragment2(), new Fragment3())
                .pageBoundaries(100)
                .create();

        // pager
        SMAViewPager pager2 = (SMAViewPager) findViewById(R.id.pager2);
        pager2.fragmentManager(getFragmentManager())
                .setFragments(new Fragment1(), new Fragment2(), new Fragment3())
                .pageBoundaries(300, 50)
                .create();
    }
}
