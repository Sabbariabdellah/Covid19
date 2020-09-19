package fpt.sim.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class welcomeScreen extends FragmentActivity {

    private static final int NUM_PAGES = 3;
    public static ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    public static Button start;
    public static Button next;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new welcomeScreenAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setUserInputEnabled(false);
        dotsLayout = findViewById(R.id.dotsLayout);
        addDots(0);
        viewPager.registerOnPageChangeCallback(changeCallback);
        start = findViewById(R.id.start);
        next = findViewById(R.id.next);

    }

    public void goMain(View view) {
        SharedPreferences.Editor editor = splashScreen.sharedPreferences.edit();
        editor.putBoolean("first", false);
        editor.commit();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void nextSlide(View view) {
        viewPager.setCurrentItem(currentPosition + 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private class welcomeScreenAdapter extends FragmentStateAdapter {
        public welcomeScreenAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new welcome1();
                case 1:
                    return new verify_phone();
                default:
                    return new activate_bluetooth();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    public void addDots(int pos) {
        dots = new TextView[NUM_PAGES];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager2.OnPageChangeCallback changeCallback = new ViewPager2.OnPageChangeCallback() {

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            addDots(position);
            if (position == 0) {
                start.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                addDots(0);
            } else if (position == 1) {
                start.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
            } else {
                start.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
            }
        }
    };

}