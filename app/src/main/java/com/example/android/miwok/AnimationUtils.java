package com.example.android.miwok;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by mdiaz on 05.07.17.
 */

public final class AnimationUtils {

    private AnimationUtils(){
    }

    // Basic Fade in Animation
    public static void setAnimation(final View viewToAnimate) {

        ValueAnimator va = ValueAnimator.ofFloat(0, 1);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                viewToAnimate.setAlpha(value);
            }
        });
        va.setDuration(500);
        va.start();
    }
}
