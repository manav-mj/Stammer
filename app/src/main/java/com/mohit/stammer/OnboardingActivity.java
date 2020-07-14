package com.mohit.stammer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class OnboardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Button nextButton;

    IntroPagerAdapter adapter = new IntroPagerAdapter();

    int[] layouts = {R.layout.layout_onboarding_weekly_tasks, R.layout.layout_onboarding_resources, R.layout.layout_onboarding_community};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onboarding);

//        hideBackButton();

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        nextButton = findViewById(R.id.next_button);


        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(layouts.length);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    nextButton.setText("Let's Start");
                } else {
                    nextButton.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void nextClicked(View view) {
        if (viewPager.getCurrentItem() < layouts.length) {
            int pos = viewPager.getCurrentItem();
            switch (pos) {
                case 2:
                    nextButton.setText("Let's Start");
                    forwardViewPager();
                    break;
                default:
                    nextButton.setText("Next");
                    forwardViewPager();
            }
        }
    }

    private void forwardViewPager() {
        if (viewPager.getCurrentItem() < layouts.length - 1)
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        else
            redirect();
    }

    private void redirect() {
        SharedPreferences sp = getSharedPreferences("stammer", MODE_PRIVATE);
        sp.edit().putBoolean("onboarding", true).apply();
        startHomeActivity();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(OnboardingActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    class IntroPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public IntroPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
