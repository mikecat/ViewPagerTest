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

        final TextView posView = (TextView)findViewById(R.id.insPosition);
        final TextView valueView = (TextView)findViewById(R.id.insValue);
        findViewById(R.id.insButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posString = posView.getText().toString();
                String value = valueView.getText().toString();
                if (posString.equals("")) {
                    itemList.add(value);
                } else {
                    try {
                        int pos = Integer.parseInt(posString);
                        itemList.add(pos, value);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.invalid_pos, Toast.LENGTH_SHORT).show();
                    } catch (IndexOutOfBoundsException e) {
                        Toast.makeText(MainActivity.this, R.string.oor_pos, Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.setItemList(itemList);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
