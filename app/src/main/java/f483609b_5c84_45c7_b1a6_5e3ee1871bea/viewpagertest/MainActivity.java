package f483609b_5c84_45c7_b1a6_5e3ee1871bea.viewpagertest;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> itemList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            itemList.add("item " + i);
        }

        FragmentManager manager = getSupportFragmentManager();
        final ViewPager vp = (ViewPager)findViewById(R.id.viewPager);
        final MyPagerAdapter adapter = new MyPagerAdapter(manager);
        adapter.setItemList(itemList);
        vp.setAdapter(adapter);
    }
}
