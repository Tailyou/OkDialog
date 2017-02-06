package com.hengda.zwf.hddialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HProgressDialog extends Dialog {

    private Context mContext = null;
    private LinearLayout rootPanel;
    private ImageView mImg = null;
    private ProgressBar progressBar = null;
    private TextView mTxt = null;
    private Animation animation = null;
    private AnimationDrawable animationDrawable = null;
    private BaseEffects baseEffects;

    public HProgressDialog(Context context) {
        super(context, R.style.hd_progress_dialog);
        mContext = context;
        init();
    }

    public HProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    public void init() {
        View dialogContainer = View.inflate(mContext, R.layout.layout_hd_progress_dialog,
                null);
        rootPanel = (LinearLayout) dialogContainer.findViewById(R.id.rootPanel);
        mImg = (ImageView) dialogContainer.findViewById(R.id.imageView);
        progressBar = (ProgressBar) dialogContainer.findViewById(R.id.progressBar);
        mTxt = (TextView) dialogContainer.findViewById(R.id.textView);
        setContentView(dialogContainer);
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (baseEffects != null) {
                    baseEffects.setDuration(500);
                    baseEffects.start(rootPanel);
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        if (animation != null) {
            mImg.startAnimation(animation);
        }
    }

    public HProgressDialog withMsg(CharSequence msg) {
        mTxt.setText(msg);
        mTxt.setVisibility(View.VISIBLE);
        return this;
    }

    public HProgressDialog withMsg(int resId) {
        mTxt.setText(mContext.getString(resId));
        mTxt.setVisibility(View.VISIBLE);
        return this;
    }

    public HProgressDialog withBgColor(int color) {
        if (color == Color.TRANSPARENT) {
            rootPanel.setBackgroundColor(color);
        } else {
            rootPanel.getBackground().setColorFilter(ColorUtils.getColorFilter(color));
        }
        return this;
    }

    public HProgressDialog withBgColor(String colorString) {
        rootPanel.getBackground().setColorFilter(ColorUtils.getColorFilter(Color.parseColor
                (colorString)));
        return this;
    }

    public HProgressDialog withEffects(BaseEffects baseEffects) {
        this.baseEffects = baseEffects;
        return this;
    }

    public HProgressDialog frameAnim(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setIndeterminateDrawable(mContext.getDrawable(resId));
            progressBar.setVisibility(View.VISIBLE);
        } else {
            mImg.setImageResource(resId);
            mImg.setVisibility(View.VISIBLE);
            animationDrawable = (AnimationDrawable) mImg.getDrawable();
        }
        return this;
    }

    public HProgressDialog tweenAnim(int drawable, int anim) {
        mImg.setImageResource(drawable);
        mImg.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(mContext, anim);
        return this;
    }

    public HProgressDialog withTypeface(Typeface typeface) {
        mTxt.setTypeface(typeface);
        return this;
    }

    public HProgressDialog cancelable(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    public HProgressDialog outsideCancelable(boolean outsideCancelable) {
        setCanceledOnTouchOutside(outsideCancelable);
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animation = null;
        animationDrawable = null;
    }

}
