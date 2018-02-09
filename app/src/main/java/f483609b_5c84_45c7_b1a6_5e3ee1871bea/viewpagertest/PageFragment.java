package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment {
    private final static String ITEMS = "items";
    private final static int[] itemDisplayIDs = {
        R.id.textView1, R.id.textView2, R.id.textView3
    };

    public static PageFragment newInstance(String[] items) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(ITEMS, items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Log.d("fragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vert_3items_fragment, container, false);
        String[] items = getArguments().getStringArray(ITEMS);
        if (items != null) {
            for (int i = 0; i < items.length && i < itemDisplayIDs.length; i++) {
                ((TextView)view.findViewById(itemDisplayIDs[i])).setText(items[i]);
            }
        }
        Log.d("fragment", "onCreateView");
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("fragment", "onPause");
    }
}
