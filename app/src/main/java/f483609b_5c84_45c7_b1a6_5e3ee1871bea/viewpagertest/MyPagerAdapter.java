package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private final static int ITEMS_PER_PAGE = 3;

    private List<String> itemList = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setItemList(List<String> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("adapter", "getItem(" + position + ")");
        int baseIndex = Math.max(0, Math.min(position * ITEMS_PER_PAGE, itemList.size()));
        int elementNum = Math.min(itemList.size() - baseIndex, ITEMS_PER_PAGE);
        String[] items = itemList.subList(baseIndex, baseIndex + elementNum)
                .toArray(new String[elementNum]);
        return PageFragment.newInstance(items);
    }

    @Override
    public int getCount() {
        Log.d("adapter", "getCount() size = " + itemList.size());
        return Math.max(1, (itemList.size() + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE);
    }
}
