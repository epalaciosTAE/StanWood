package kalpesh.mac.com.stanwood_test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import kalpesh.mac.com.stanwood_test.fragment.MovieFragment;
import kalpesh.mac.com.stanwood_test.model.data.Item;

/**
 * Created by Eduardo on 08/02/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = {"Top", "Upcoming"};
    private List<Item> items;

    public ViewPagerAdapter(FragmentManager fm, List<Item> items) {
        super(fm);
        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MovieFragment.newInstance((ArrayList<Item>) items);
        } else {
            return MovieFragment.newInstance((ArrayList<Item>) items);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
