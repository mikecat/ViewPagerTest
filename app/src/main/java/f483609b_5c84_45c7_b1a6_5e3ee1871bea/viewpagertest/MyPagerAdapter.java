package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest.Page.ITEMS_PER_PAGE;

public class MyPagerAdapter extends PagerAdapter {

    private List<String> itemList = new ArrayList<>();
    private int myAdapterID;
    private Context context;

    public MyPagerAdapter(Context context) {
        this.context = context;
    }

    public void setItemList(List<String> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("adapter", "getItem(" + position + ")");
        Page page = new Page(context, position * ITEMS_PER_PAGE, container, this);
        container.addView(page.getView());
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((Page)object).getView());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object instanceof Page && view == ((Page)object).getView();
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
