package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Page {
    private final static String START_OFFSET = "start_offset";
    private final static String ADAPTER_ID = "adapter_id";
    private final static int[] itemDisplayIDs = {
        R.id.textView1, R.id.textView2, R.id.textView3
    };
    public final static int ITEMS_PER_PAGE = itemDisplayIDs.length;

    private View view;
    private MyPagerAdapter adapter;
    private boolean dataValid;
    private int startOffset;
    private String[] items;

    private class Observer extends DataSetObserver {
        @Override
        public void onChanged() {
            dataValid = false;
            displayData();
        }
    }

    private void displayData() {
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) +
                " displayData() with dataValid = " + dataValid);
        if (!dataValid) {
            items = adapter != null ? adapter.getItemData(startOffset) : new String[0];
            if (items != null) {
                for (int i = 0; i < itemDisplayIDs.length; i++) {
                    ((TextView) view.findViewById(itemDisplayIDs[i]))
                            .setText(i < items.length ? items[i] : "");
                }
            }
            dataValid = true;
        }
    }

    public Page(Context context, int startOffset, ViewGroup container, MyPagerAdapter adapter) {
        view = LayoutInflater.from(context).inflate(R.layout.items_fragment, container, false);
        this.startOffset = startOffset;
        this.adapter = adapter;
        adapter.registerDataSetObserver(new Observer());
        dataValid = false;
        displayData();
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onCreate()");
    }

    public View getView() {
        return view;
    }
}
