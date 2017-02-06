package com.hengda.zwf.hddialog;

import android.view.View;

import static com.nineoldandroids.animation.ObjectAnimator.ofFloat;

public class SlideTop extends BaseEffects {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ofFloat(view, "translationY", -300, 0).setDuration(mDuration),
                ofFloat(view, "alpha", 0, 1).setDuration(mDuration * 3 / 2)
        );
    }

}
