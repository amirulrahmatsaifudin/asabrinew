package com.example.asabri.activity.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.asabri.R;
import com.example.asabri.activity.LoginActivity;


import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
        private LinearLayout pager_indicator;
        private int dotsCount;
        private ImageView[] dots;
        private ViewPager onboard_pager;
        private OnBoard_Adapter mAdapter;
        private ImageView onboarfinish;
        private Button btn_get_started;
        int previous_pos=0;


        ArrayList<OnBoardItem> onBoardItems=new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_on_boarding);

            onboard_pager = (ViewPager) findViewById(R.id.pager_introduction);
            pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
            onboarfinish = (ImageView) findViewById(R.id.imageend);
            btn_get_started = (Button) findViewById(R.id.btn_get_started);

            loadData();

            mAdapter = new OnBoard_Adapter(this,onBoardItems);
            onboard_pager.setAdapter(mAdapter);
            onboard_pager.setCurrentItem(0);
            onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    // Change the current position intimation

                    for (int i = 0; i < dotsCount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));


                    int pos=position+1;

                    if(pos==dotsCount&&previous_pos==(dotsCount-1))
                        loadFirstScreen();
                    else if(pos==(dotsCount-1)&&previous_pos==dotsCount)
                        loaddLastScreen();

                    previous_pos=pos;
                }


                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            btn_get_started.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //open main activity
                    Intent mainActivity = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(mainActivity);
                    finish();
                }
            });
            setUiPageViewController();
        }

        // Load data into the viewpager

        public void loadData()
        {

            int[] header = {R.string.ob_header1, R.string.ob_header2,R.string.ob_header3};
            int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
            int[] imageId = {R.drawable.boarding1, R.drawable.boarding2, R.drawable.boarding3};

            for(int i=0;i<imageId.length;i++)
            {
                OnBoardItem item=new OnBoardItem();
                item.setImageID(imageId[i]);
                item.setTitle(getResources().getString(header[i]));
                item.setDescription(getResources().getString(desc[i]));
                onBoardItems.add(item);
            }
        }

          // setup the
        private void setUiPageViewController() {
            dotsCount = mAdapter.getCount();
            dots = new ImageView[dotsCount];
            for (int i = 0; i < dotsCount; i++) {
                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(6, 0, 6, 0);
                pager_indicator.addView(dots[i], params);
            }
            dots[0].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));
        }

        private void loaddLastScreen(){
            pager_indicator.setVisibility(View.INVISIBLE);
            onboard_pager.setVisibility(View.INVISIBLE);
            pager_indicator.setVisibility(View.INVISIBLE);
            btn_get_started.setVisibility(View.VISIBLE);
            onboarfinish.setVisibility(View.VISIBLE);
        }

        private  void  loadFirstScreen(){
            pager_indicator.setVisibility(View.VISIBLE);
            onboard_pager.setVisibility(View.VISIBLE);
            pager_indicator.setVisibility(View.VISIBLE);
            btn_get_started.setVisibility(View.INVISIBLE);
            onboarfinish.setVisibility(View.INVISIBLE);
        }

    }


