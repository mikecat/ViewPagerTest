package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest.PageFragment.ITEMS_PER_PAGE;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<String> itemList = new ArrayList<>();
    private int myAdapterID;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        myAdapterID = PageFragment.registerAdapter(this);
    }

    public void setItemList(List<String> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("adapter", "getItem(" + position + ")");
        return PageFragment.newInstance(position * ITEMS_PER_PAGE, myAdapterID);
    }

    @Override
    public int getCount() {
        Log.d("adapter", "getCount() size = " + itemList.size());
        return Math.max(1, (itemList.size() + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE);
    }

    public String[] getItemData(int startOffset) {
        int elementNum = Math.min(itemList.size() - startOffset, ITEMS_PER_PAGE);
        return itemList.subList(startOffset, startOffset + elementNum)
                .toArray(new String[elementNum]);
    }
}
