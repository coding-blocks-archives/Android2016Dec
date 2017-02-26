package com.codingblocks.unittestsinterestcalculator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Created by championswimmer on 26/02/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Rule
    public final ActivityTestRule<MainActivity> act = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUpActivity () {
        mainActivity = act.getActivity();
    }

    @Test
    public void interestCalculation_isCorrect () throws Exception {


        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.etInterest.setText(String.valueOf(10));
                mainActivity.etPrincial.setText(String.valueOf(100));
                mainActivity.etYears.setText(String.valueOf(1));

                mainActivity.btnCalcInterest.performClick();

                float amount = Float.valueOf(mainActivity.tvAmount.getText().toString());
                assertEquals(110, amount, 0.001);
            }
        });
    }

}
