package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment {
    private final static String START_OFFSET = "start_offset";
    private final static int[] itemDisplayIDs = {
        R.id.textView1, R.id.textView2, R.id.textView3
    };
    public final static int ITEMS_PER_PAGE = itemDisplayIDs.length;

    private MyPagerAdapter adapter;
    private boolean dataValid;
    private boolean viewValid;
    private boolean isShown;
    private int startOffset;
    private String[] items;

    private class Observer extends DataSetObserver {
        @Override
        public void onChanged() {
            dataValid = false;
            if (isShown) displayData();
        }
    }

    private Observer newObserver() {
        return new Observer();
    }

    private void displayData() {
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) +
                " displayData() with dataValid = " + dataValid + ", viewValid = " + viewValid);
        if (!dataValid) {
            items = adapter != null ? adapter.getItemData(startOffset) : new String[0];
            dataValid = true;
            viewValid = false;
        }
        if (!viewValid) {
            if (items != null) {
                View v = getView();
                if (v != null) {
                    for (int i = 0; i < itemDisplayIDs.length; i++) {
                        ((TextView) v.findViewById(itemDisplayIDs[i]))
                                .setText(i < items.length ? items[i] : "");
                    }
                }
            }
            viewValid = true;
        }
    }

    public static PageFragment newInstance(int startOffset, MyPagerAdapter adapter) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(START_OFFSET, startOffset);
        fragment.setArguments(bundle);
        fragment.adapter = adapter;
        fragment.dataValid = false;
        fragment.viewValid = false;
        fragment.isShown = false;
        adapter.registerDataSetObserver(fragment.newObserver());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.startOffset = getArguments().getInt(START_OFFSET);
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onCreateView()");
        viewValid = false;
        return inflater.inflate(R.layout.vert_3items_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onStart()");
        isShown = true;
        displayData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onStop()");
        isShown = false;
    }
}
