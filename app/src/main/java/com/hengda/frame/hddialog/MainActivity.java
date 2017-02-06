package com.hengda.frame.hddialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hengda.zwf.hddialog.DialogClickListener;
import com.hengda.zwf.hddialog.HDialogBuilder;
import com.hengda.zwf.hddialog.HProgressDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    HDialogBuilder hDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.prgDlgTAnim, R.id.prgDlgFAnim, R.id.bldDlgNormal, R.id.bldDlgSglBtn, R.id.bldDlgCustView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prgDlgTAnim:
                showPrgDlgTAnim();
                break;
            case R.id.prgDlgFAnim:
                showPrgDlgFAnim();
                break;
            case R.id.bldDlgNormal:
                showBldDlgNormal();
                break;
            case R.id.bldDlgSglBtn:
                showBldDlgSglBtn();
                break;
            case R.id.bldDlgCustView:
                //showCustomDlg();
                /*showCustomDlg("注销", "退出账号可能会使连续登录记录归零，确定退出？",
                        "确定退出", "取消",
                        new DialogClickListener() {
                            @Override
                            public void p() {
                                hDialogBuilder.dismiss();
                            }

                            @Override
                            public void n() {
                                hDialogBuilder.dismiss();
                            }
                        });*/
                DialogCenter.showCustomDlg(mContext, "注销", "退出账号可能会使连续登录记录归零，确定退出？",
                        "确定退出", "取消",
                        new DialogClickListener() {
                            @Override
                            public void p() {
                                DialogCenter.hideDialog();
                            }

                            @Override
                            public void n() {
                                DialogCenter.hideDialog();
                            }
                        });
                break;
        }
    }

    /**
     * 该方法通过提取文字参数和点击事件继续封装
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/2/6 9:37
     */
    private void showCustomDlg() {
        HDialogBuilder hDialogBuilder = new HDialogBuilder(mContext);

        View customView = View.inflate(mContext, R.layout.dialog_custom_view_all, null);
        TextView tvTitle = HdTool.getView(customView, R.id.tvTitle);
        TextView tvMsg = HdTool.getView(customView, R.id.tvMsg);
        TextView btnYes = HdTool.getView(customView, R.id.btnYes);
        TextView btnNo = HdTool.getView(customView, R.id.btnNo);
        tvTitle.setText("注销");
        tvMsg.setText("退出账号可能会使连续登录记录归零，确定退出？");
        btnYes.setText("确定退出");
        btnNo.setText("取消");
        btnYes.setOnClickListener(v -> hDialogBuilder.dismiss());
        btnNo.setOnClickListener(v -> hDialogBuilder.dismiss());

        hDialogBuilder.setCustomView(customView)
                .dlgColor(Color.TRANSPARENT)
                .cancelable(false)
                .show();
    }

    /**
     * 封装好的方法可以写到DialogCenter工具类中
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/2/6 9:39
     */
    private void showCustomDlg(String title, String msg, String txtYes, String txtNo,
                               DialogClickListener dialogClickListener) {
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

    private void showBldDlgNormal() {
        HDialogBuilder hDialogBuilder = new HDialogBuilder(mContext);
        hDialogBuilder
                .withIcon(R.mipmap.ic_launcher)
                .dlgColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                .withTitle("温馨提示")
                .withMsg("恒达Android团队越来越好！")
                .pBtnText("确定")
                .pBtnClickListener(v -> hDialogBuilder.dismiss())
                .nBtnText("取消")
                .nBtnClickListener(v -> hDialogBuilder.dismiss())
                .cancelable(true);
        hDialogBuilder.show();
    }

    private void showBldDlgSglBtn() {
        HDialogBuilder hDialogBuilder = new HDialogBuilder(mContext);
        hDialogBuilder
                .withIcon(R.mipmap.ic_launcher)
                .dlgColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                .withTitle("温馨提示")
                .withMsg("恒达Android团队越来越好！")
                .pBtnText("确定")
                .pBtnClickListener(v -> hDialogBuilder.dismiss())
                .cancelable(true);
        hDialogBuilder.show();
    }

    private void showPrgDlgTAnim() {
        new HProgressDialog(mContext)
                .withMsg("请稍后...")
                .tweenAnim(R.drawable.progress_roate, R.anim.prg_anim_tween)
                .outsideCancelable(false)
                .cancelable(true)
                .show();
    }

    private void showPrgDlgFAnim() {
        new HProgressDialog(mContext)
                .withMsg("请稍后...")
                .frameAnim(R.drawable.prg_anim_frame)
                .outsideCancelable(false)
                .cancelable(true)
                .show();
    }

}
