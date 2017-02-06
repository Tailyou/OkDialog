package com.hengda.frame.hddialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.hengda.zwf.hddialog.DialogClickListener;
import com.hengda.zwf.hddialog.HDialogBuilder;
import com.hengda.zwf.hddialog.HProgressDialog;


/**
 * 作者：Tailyou （祝文飞）
 * 时间：2016/5/26 19:03
 * 邮箱：tailyou@163.com
 * 描述：Dialog工具类
 */
public class DialogCenter {

    private static HDialogBuilder hDialogBuilder;

    /**
     * 封装好的方法可以写到DialogCenter工具类中，供直接调用
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/2/6 9:39
     */
    public static void showCustomDlg(Context mContext, String title, String msg, String txtYes, String txtNo,
                               DialogClickListener dialogClickListener) {
        hideDialog();
        hDialogBuilder = new HDialogBuilder(mContext);
        View customView = View.inflate(mContext, R.layout.dialog_custom_view_all, null);
        TextView tvTitle = HdTool.getView(customView, R.id.tvTitle);
        TextView tvMsg = HdTool.getView(customView, R.id.tvMsg);
        TextView btnYes = HdTool.getView(customView, R.id.btnYes);
        TextView btnNo = HdTool.getView(customView, R.id.btnNo);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        btnYes.setText(txtYes);
        btnNo.setText(txtNo);
        btnYes.setOnClickListener(v -> dialogClickListener.p());
        btnNo.setOnClickListener(v -> dialogClickListener.n());
        hDialogBuilder.setCustomView(customView)
                .dlgColor(Color.TRANSPARENT)
                .cancelable(false)
                .show();
    }

    /**
     * 隐藏Dialog
     */
    public static void hideDialog() {
        if (hDialogBuilder != null) {
            hDialogBuilder.dismiss();
            hDialogBuilder = null;
        }
    }

}
