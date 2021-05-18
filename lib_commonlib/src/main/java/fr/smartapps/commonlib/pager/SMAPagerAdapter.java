package fr.smartapps.commonlib.pager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincentchann on 23/08/16.
 */
public class SMAPagerAdapter extends FragmentPagerAdapter {

    /*
    Attributes
     */
    protected List<Fragment> fragmentList = new ArrayList<>();

    /*
    Constructor
     */
    public SMAPagerAdapter(FragmentManager manager) {
        super(manager);

    }

    /*
    Override mandatory methods
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    /*
    Public methods
     */
    public SMAPagerAdapter setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        return this;
    }
}
