package asia.fivejuly.rouletteexample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.Random;

/**
 * Author Pham Quy Hai
 * Github: https://github.com/KaKaVip/android-roulette-example
 * Copyright 2016
 */

public class MainActivity extends AppCompatActivity {

    private ImageView imageRoulette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageRoulette = (ImageView) findViewById(R.id.image_roulette);
    }

    public void actionRoulette(View view) {
        int corner = 360/38; // corner for point
        int randPosition = corner * new Random().nextInt(38); // random point
        int MIN = 5; // min rotation
        int MAX = 9; // max rotation
        long TIME_IN_WHEEL = 1000;  // time in one rotation
        int randRotation = MIN + new Random().nextInt(MAX-MIN); // random rotation
        int truePosition =  randRotation * 360 + randPosition;
        long totalTime = TIME_IN_WHEEL * randRotation + (TIME_IN_WHEEL/360) * randPosition;

        Log.d("ROULETTE_ACTION","randPosition : " + randPosition
                + " randRotation : " + randRotation
                + " totalTime : " + totalTime
                + " truePosition : " + truePosition);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0f,(float)truePosition);
        animator.setDuration(totalTime);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                imageRoulette.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                imageRoulette.setEnabled(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }
}
