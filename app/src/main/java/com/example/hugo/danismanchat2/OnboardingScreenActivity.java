package com.example.hugo.danismanchat2;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OnboardingScreenActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout nDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button nNextButton;
    private Button mBackbutton;

    private int currentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideView);
        nDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        nNextButton = (Button) findViewById(R.id.next);
        mBackbutton = (Button) findViewById(R.id.previous);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        nNextButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if(currentPage == 2){
                    goToTheMainController();
                }else {


                    mSlideViewPager.setCurrentItem(currentPage + 1);

                }
            }
        });

        mBackbutton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(currentPage - 1);
            }
        });



    }

    private void goToTheMainController() {
        Intent mainControllerIntent = new Intent(OnboardingScreenActivity.this, MainController.class);
        startActivity(mainControllerIntent);

    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        nDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            nDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhire));

        }

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            currentPage = position;

                if (position == 0){
                    nNextButton.setEnabled(true);
                    mBackbutton.setEnabled(false);
                    mBackbutton.setVisibility(View.INVISIBLE);

                    nNextButton.setText("Next");
                    mBackbutton.setText("");

                }else if(position == mDots.length - 1){
                    nNextButton.setEnabled(true);
                    mBackbutton.setEnabled(true);
                    mBackbutton.setVisibility(View.VISIBLE);

                    nNextButton.setText("Bitir");
                    mBackbutton.setText("Geri");


                }else{
                    nNextButton.setEnabled(true);
                    mBackbutton.setEnabled(true);
                    mBackbutton.setVisibility(View.VISIBLE);

                    nNextButton.setText("İleri");
                    mBackbutton.setText("Geri");


                }

        }



        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
