package com.sackcentury.shinebutton;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ShineButton shineButton;
    ShineButton porterShapeImageView1;
    ShineButton porterShapeImageView2;
    ShineButton porterShapeImageView3;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shineButton = (ShineButton) findViewById(R.id.po_image0);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.wrapper);

        if (shineButton != null)
            shineButton.init(this);
        porterShapeImageView1 = (ShineButton) findViewById(R.id.po_image1);
        if (porterShapeImageView1 != null)
            porterShapeImageView1.init(this);
        porterShapeImageView2 = (ShineButton) findViewById(R.id.po_image2);
        if (porterShapeImageView2 != null)
            porterShapeImageView2.init(this);
        porterShapeImageView3 = (ShineButton) findViewById(R.id.po_image3);
        if (porterShapeImageView3 != null)
            porterShapeImageView3.init(this);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new ListAdapter());

        ShineButton shineButtonJava = new ShineButton(this);

        shineButtonJava.setBtnColor(Color.GRAY);
        shineButtonJava.setBtnFillColor(Color.RED);
        shineButtonJava.setShapeResource(R.raw.heart);
        shineButtonJava.setAllowRandomColor(true);
        shineButtonJava.setShineSize(100);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        shineButtonJava.setLayoutParams(layoutParams);
        if (linearLayout != null) {
            linearLayout.addView(shineButtonJava);
        }


        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });
        shineButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                Log.e(TAG, "click " + checked);
            }
        });

        porterShapeImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });
        porterShapeImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });


    }

    List<Data> dataList = new ArrayList<>();


    class ListAdapter extends BaseAdapter {
        public ListAdapter() {
            for (int i = 0; i < 20; i++) {
                dataList.add(new Data());
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int i) {
            return dataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, null);
            }
            ShineButton button = (ShineButton) view.findViewById(R.id.po_image);
            TextView textView = (TextView) view.findViewById(R.id.text_item_id);
            textView.setText("ShineButton Position " + i);
            button.setChecked(dataList.get(i).checked);
            button.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(View view, boolean checked) {
                    dataList.get(i).checked = checked;
                }
            });
            return view;
        }
    }

    class Data {
        public int position;
        public boolean checked;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fragment_page:
                showFragmentPage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFragmentPage() {
        new FragmentDemo().showFragment(getSupportFragmentManager());
    }
}
