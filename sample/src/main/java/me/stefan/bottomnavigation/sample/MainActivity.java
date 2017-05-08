package me.stefan.bottomnavigation.sample;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.stefan.bottomnavigation.BadgeItem;
import me.stefan.bottomnavigation.BottomNavigationBar;
import me.stefan.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.message)
    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        BottomNavigationBar bottomNavigationBar =
                (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        Resources res = getResources();
        String[] titleArray = res.getStringArray(R.array.main_tab_title);
        TypedArray a = res.obtainTypedArray(R.array.main_tab_icon);
        for (int i = 0; i < a.length(); i++) {
            int iconResId = a.getResourceId(i, 0);
            BottomNavigationItem item = new BottomNavigationItem(iconResId, titleArray[i]);
            if (0 == i) {
                BadgeItem numberBadgeItem = new BadgeItem()
                        .setBorderWidth(0)
                        .setBackgroundColorResource(R.color.red)
                        .setText("5")
                        .setHideOnSelect(false);
                item.setTextBadgeItem(numberBadgeItem);
            }
            bottomNavigationBar.addItem(item);
        }
        a.recycle();
        bottomNavigationBar.setActiveColor(R.color.tab_active_color);
        bottomNavigationBar.setInActiveColor(R.color.tab_inactive_color);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setTabSelectedListener(mOnTabSelectedListener);
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.initialise();
    }

    private BottomNavigationBar.OnTabSelectedListener mOnTabSelectedListener
            = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            switch (position) {
                case 0:
                    mTextMessage.setText(R.string.title_vicinity);
                    break;
                case 1:
                    mTextMessage.setText(R.string.title_forum);
                    break;
                case 2:
                    mTextMessage.setText(R.string.title_message);
                    break;
                case 3:
                    mTextMessage.setText(R.string.title_mine);
                    break;
            }
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };

}
