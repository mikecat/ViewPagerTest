package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {
    private final static List<MyPagerAdapter> adapters = new ArrayList<>();
    public static int registerAdapter(MyPagerAdapter adapter) {
        int index = adapters.size();
        adapters.add(adapter);
        return index;
    }

    private final static String START_OFFSET = "start_offset";
    private final static String ADAPTER_ID = "adapter_id";
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

    public static PageFragment newInstance(int startOffset, int adapterID) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(START_OFFSET, startOffset);
        bundle.putInt(ADAPTER_ID, adapterID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        startOffset = getArguments().getInt(START_OFFSET);
        adapter = adapters.get(getArguments().getInt(ADAPTER_ID));
        adapter.registerDataSetObserver(new Observer());
        dataValid = false;
        viewValid = false;
        isShown = false;
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("fragment", "page" + (startOffset / ITEMS_PER_PAGE) + " onCreateView()");
        viewValid = false;
        return inflater.inflate(R.layout.items_fragment, container, false);
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
