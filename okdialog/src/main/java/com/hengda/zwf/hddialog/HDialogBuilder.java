package com.hengda.zwf.hddialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HDialogBuilder extends Dialog {

    private LinearLayout rootPanel;
    private LinearLayout topPanel;
    private LinearLayout customPanel;
    private View mDivider;
    private TextView mTitle;
    private TextView mMsg;
    private ImageView mIcon;
    private Button mBtnP;
    private Button mBtnN;
    private BaseEffects inEffect;

    public HDialogBuilder(Context context) {
        super(context, R.style.hd_dialog_dim);
        init(context);
    }

    public HDialogBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private void init(Context context) {
        View dialogContainer = View.inflate(context, R.layout.layout_hd_builder_dialog, null);
        rootPanel = (LinearLayout) dialogContainer.findViewById(R.id.rootPanel);
        topPanel = (LinearLayout) dialogContainer.findViewById(R.id.topPanel);
        customPanel = (LinearLayout) dialogContainer.findViewById(R.id.customPanel);
        mIcon = (ImageView) dialogContainer.findViewById(R.id.icon);
        mDivider = dialogContainer.findViewById(R.id.titleDivider);
        mTitle = (TextView) dialogContainer.findViewById(R.id.alertTitle);
        mMsg = (TextView) dialogContainer.findViewById(R.id.alertMsg);
        mBtnP = (Button) dialogContainer.findViewById(R.id.dialog_btn_p);
        mBtnN = (Button) dialogContainer.findViewById(R.id.dialog_btn_n);
        setContentView(dialogContainer);
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (inEffect != null) {
                    inEffect.start(rootPanel);
                }
            }
        });
    }

    public HDialogBuilder withTitle(CharSequence title) {
        topPanel.setVisibility(View.VISIBLE);
        mTitle.setText(title);
        return this;
    }

    public HDialogBuilder withTitle(int title) {
        topPanel.setVisibility(View.VISIBLE);
        mTitle.setText(title);
        return this;
    }

    public HDialogBuilder withMsg(CharSequence msg) {
        mMsg.setVisibility(View.VISIBLE);
        mMsg.setText(msg);
        return this;
    }

    public HDialogBuilder withMsg(int msg) {
        mMsg.setVisibility(View.VISIBLE);
        mMsg.setText(msg);
        return this;
    }

    public HDialogBuilder withIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }

    public HDialogBuilder withInEffect(BaseEffects baseEffects) {
        this.inEffect = baseEffects;
        return this;
    }

    public HDialogBuilder titleColor(int color) {
        mDivider.setVisibility(View.VISIBLE);
        mTitle.setTextColor(color);
        return this;
    }

    public HDialogBuilder dividerColor(int color) {
        mDivider.setVisibility(View.VISIBLE);
        mDivider.setBackgroundColor(color);
        return this;
    }

    public HDialogBuilder msgColor(int color) {
        mMsg.setTextColor(color);
        return this;
    }

    public HDialogBuilder dlgColor(int color) {
        if (color == Color.TRANSPARENT) {
            rootPanel.setBackgroundColor(color);
        } else {
            //use color filter to keep round corner
            rootPanel.getBackground().setColorFilter(ColorUtils.getColorFilter(color));
        }
        return this;
    }

    public HDialogBuilder withBgDlg(int bgResId) {
        rootPanel.setBackgroundResource(bgResId);
        return this;
    }

    public HDialogBuilder withBgBtn(int resId) {
        mBtnP.setBackgroundResource(resId);
        mBtnN.setBackgroundResource(resId);
        return this;
    }

    public HDialogBuilder pBtnText(CharSequence text) {
        mBtnP.setVisibility(View.VISIBLE);
        mBtnP.setText(text);
        return this;
    }

    public HDialogBuilder pBtnText(int text) {
        mBtnP.setVisibility(View.VISIBLE);
        mBtnP.setText(text);
        return this;
    }

    public HDialogBuilder nBtnText(CharSequence text) {
        mBtnN.setVisibility(View.VISIBLE);
        mBtnN.setText(text);
        return this;
    }

    public HDialogBuilder nBtnText(int text) {
        mBtnN.setVisibility(View.VISIBLE);
        mBtnN.setText(text);
        return this;
    }

    public HDialogBuilder pBtnClickListener(View.OnClickListener click) {
        mBtnP.setOnClickListener(click);
        return this;
    }

    public HDialogBuilder nBtnClickListener(View.OnClickListener click) {
        mBtnN.setOnClickListener(click);
        return this;
    }

    public HDialogBuilder setCustomView(View view) {
        if (customPanel.getChildCount() > 0) {
            customPanel.removeAllViews();
        }
        customPanel.addView(view);
        customPanel.setVisibility(View.VISIBLE);
        return this;
    }

    public HDialogBuilder setDlgWidth(int width) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return this;
    }

    public HDialogBuilder cancelable(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    public HDialogBuilder outsideCancelable(boolean outsideCancelable) {
        setCanceledOnTouchOutside(outsideCancelable);
        return this;
    }

    public HDialogBuilder setTypeface(Typeface typeface) {
        mTitle.setTypeface(typeface);
        mMsg.setTypeface(typeface);
        mBtnP.setTypeface(typeface);
        mBtnN.setTypeface(typeface);
        return this;
    }

}
